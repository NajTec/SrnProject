﻿using SrnClient.Models;
using System;
using System.Collections.Generic;
using System.IO;
using System.Linq;
using System.Security.Cryptography;
using System.Text;
using System.Threading.Tasks;

namespace SrnClient.Services
{
   public class CryptoService
   {
        public CryptoService()
        {

        }

        public PrivatePublicPair GenerateRsaKeys()
        {
            //Generate a public/private key pair.  
            RSA rsa = RSA.Create();
            PrivatePublicPair privatePublicPairs = new PrivatePublicPair();

            privatePublicPairs.rsa = rsa;

            return privatePublicPairs;
        }

        public PrivatePublicPair LoadRsaKeys(byte[] privateKey, byte[] publicKey)
        {
            int privateKeyLength = privateKey.Length;
            int publicKeyLength = publicKey.Length;

            RSA rsa = RSA.Create();
            PrivatePublicPair privatePublicPairs = new PrivatePublicPair();

            rsa.ImportRSAPrivateKey(privateKey, out privateKeyLength);
            rsa.ImportRSAPublicKey(publicKey, out publicKeyLength);

            privatePublicPairs.rsa = rsa;

            return privatePublicPairs;
        }

        public byte[][] GenerateAesKeyAndIv()
        {
            Aes aes = Aes.Create();
            aes.GenerateIV();
            aes.GenerateKey();

            byte[][] aesKeyIv = new byte[2][];
            aesKeyIv[0] = aes.Key;
            aesKeyIv[1] = aes.IV;

            return aesKeyIv;
        }

        public byte[][] EncryptAesKeyAndIv(byte[] AesKey, byte[] IV, RSA rsa)
        {
            //Create values to store encrypted symmetric keys.
            byte[] encryptedSymmetricKey;
            byte[] encryptedSymmetricIV;

            //Encrypt the symmetric key and IV.
            encryptedSymmetricKey = rsa.Encrypt(AesKey, RSAEncryptionPadding.Pkcs1);
            encryptedSymmetricIV = rsa.Encrypt(IV, RSAEncryptionPadding.Pkcs1);

            byte[][] encryptedAesKeyIv = new byte[2][];
            encryptedAesKeyIv[0] = encryptedSymmetricKey;
            encryptedAesKeyIv[1] = encryptedSymmetricIV;

            return encryptedAesKeyIv;
        }

        public byte[][] DecryptAesKeyAndIv(byte[] encryptedSymmetricKey, byte[] encryptedSymmetricIV, RSA rsa)
        {
            byte[] decryptedSymmetricKey;
            byte[] decryptedSymmetricIV;

            //Decrypt the symmetric key and IV.
            decryptedSymmetricKey = rsa.Decrypt(encryptedSymmetricKey, RSAEncryptionPadding.Pkcs1);
            decryptedSymmetricIV = rsa.Decrypt(encryptedSymmetricIV, RSAEncryptionPadding.Pkcs1);

            byte[][] decryptedAesKeyIv = new byte[2][];
            decryptedAesKeyIv[0] = decryptedSymmetricKey;
            decryptedAesKeyIv[1] = decryptedSymmetricIV;

            return decryptedAesKeyIv;
        }

        public byte[] EncryptData(byte[] symmetricKey, byte[] iv, String filepath, byte[] keyConstantA, byte[] keyConstantB)
        {

            // Check arguments.
            if (filepath == null || filepath.Length <= 0)
                throw new ArgumentNullException("cipherText");
            if (symmetricKey == null || symmetricKey.Length <= 0)
                throw new ArgumentNullException("Key");
            if (iv == null || iv.Length <= 0)
                throw new ArgumentNullException("IV");

            byte[] encryptedIv = null;

            HMACSHA256 hmac = new HMACSHA256(symmetricKey);

            byte[] keyHashCBC = hmac.ComputeHash(keyConstantA);
            byte[] keyHashECB = hmac.ComputeHash(keyConstantB);

            try
            {
                //Encrypt File with CBC
                using (Aes aesCBC = Aes.Create())
                {
                    aesCBC.Mode = CipherMode.CBC;
                    aesCBC.Key = keyHashCBC;
                    aesCBC.IV = iv;

                    using (FileStream fileStream = new FileStream(filepath + ".aes", FileMode.Create))
                    {

                        fileStream.Write(aesCBC.IV, 0, aesCBC.IV.Length);

                        using (CryptoStream cryptoStream = new CryptoStream(
                            fileStream,
                            aesCBC.CreateEncryptor(),
                            CryptoStreamMode.Write))
                        {
                            using (FileStream fsIn = new FileStream(filepath, FileMode.Open))
                            {
                                byte[] buffer = new byte[1048576];
                                int read;

                                while ((read = fsIn.Read(buffer, 0, buffer.Length)) > 0)
                                {
                                    cryptoStream.Write(buffer, 0, read);
                                }
                            }
                        }

                    }

                    Console.WriteLine("The file was encrypted.");
                }

                //Encrypt IV with ECB

                encryptedIv = SymmetricEncryptECB(iv, keyHashECB);

            }
            catch (Exception ex)
            {
                Console.WriteLine($"The encryption failed. {ex}");
            }

            return encryptedIv;
        }

        public bool DecryptData(byte[] symmetricKey, byte[] encryptedIv, String encryptedFileFilepath, String newFileFilepath, byte[] keyConstantA, byte[] keyConstantB)
        {

            // Check arguments.
            if (encryptedFileFilepath == null || encryptedFileFilepath.Length <= 0)
                throw new ArgumentNullException("cipherText");
            if (symmetricKey == null || symmetricKey.Length <= 0)
                throw new ArgumentNullException("Key");
            if (encryptedIv == null || encryptedIv.Length <= 0)
                throw new ArgumentNullException("IV");

            byte[] decryptedIv = null;

            HMACSHA256 hmac = new HMACSHA256(symmetricKey);

            byte[] keyHashCBC = hmac.ComputeHash(keyConstantA);
            byte[] keyHashECB = hmac.ComputeHash(keyConstantB);

            try
            {
                //Decrypt IV with ECB
                decryptedIv = SymmetricDecryptECB(encryptedIv, keyHashECB);

                //Decrypt File with CBC
                using (Aes aesCBC = Aes.Create())
                {
                    aesCBC.Mode = CipherMode.CBC;
                    aesCBC.Key = keyHashCBC;
                    aesCBC.IV = decryptedIv;

                    using (FileStream fileStream = new FileStream(newFileFilepath, FileMode.Create))
                    {

                        using (CryptoStream cryptoStream = new CryptoStream(
                            fileStream,
                            aesCBC.CreateDecryptor(),
                            CryptoStreamMode.Write))
                        {
                            using (FileStream fsIn = new FileStream(encryptedFileFilepath, FileMode.Open))
                            {
                                byte[] buffer = new byte[1048576];
                                byte[] random = new byte[aesCBC.IV.Length];
                                int read;

                                fsIn.Read(random, 0, random.Length); //Remove the random IV in the beginning

                                while ((read = fsIn.Read(buffer, 0, buffer.Length)) > 0)
                                {
                                    cryptoStream.Write(buffer, 0, read);
                                }
                            }
                        }

                    }

                    Console.WriteLine("The file was decrypted.");
                }
            }
            catch (Exception ex)
            {
                Console.WriteLine($"The decryption failed. {ex}");
                return false;
            }

            return true;
        }

        public byte[] EncryptKeyWithPassword(byte[] key, string password)
        {
            byte[] aes_key;

            SHA256 mySHA256 = SHA256.Create();
            aes_key = mySHA256.ComputeHash(Convert.FromBase64String(password));

            return SymmetricEncryptECB(key, aes_key);
        }

        public byte[] DecryptKeyWithPassword(byte[] key, string password)
        {
            byte[] aes_key;

            SHA256 mySHA256 = SHA256.Create();
            aes_key = mySHA256.ComputeHash(Convert.FromBase64String(password));

            return SymmetricDecryptECB(key, aes_key);
        }




    }
}
