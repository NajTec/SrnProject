using BlazorClientNew.Contracts;
using BlazorClientNew.SingeltonInstances;
using System;
using System.Collections.Generic;
using System.IO;
using System.Linq;
using System.Security.Cryptography;
using System.Threading.Tasks;
using Org.BouncyCastle.Crypto;
using Org.BouncyCastle.Crypto.Generators;
using Org.BouncyCastle.Security;
using Newtonsoft.Json;

namespace BlazorClientNew.Services
{
    public class CryptoService : ICrypto
    {
        public CryptoService()
        {

        }

        public RSA GenerateRSA()
        {
            RSA rsa = RSA.Create(2048);
            
            return rsa;
        }
        public string[] GenerateRSA2()
        {
            string[] ret = new string[2];
            RSA rsa = RSA.Create(2048);
            var random = new SecureRandom();
            var keyGenerationParameters = new KeyGenerationParameters(random, 2048);
            RsaKeyPairGenerator generator = new RsaKeyPairGenerator();
            generator.Init(keyGenerationParameters);
            var keyPair = generator.GenerateKeyPair();
            ret[0] = JsonConvert.SerializeObject(keyPair.Private);
            ret[1] = JsonConvert.SerializeObject(keyPair.Public);

            return ret;
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

        public byte[] EncryptData(byte[] symmetricKey, byte[] iv, String filepath)
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

            byte[] keyConstantA = { 0x01, 0x08, 0x07, 0x01, 0x08, 0x07, 0x01, 0x08, 0x07, 0x01, 0x08, 0x07, 0x01, 0x08, 0x07, 0x01, 0x08, 0x07, 0x01, 0x08, 0x07, 0x01, 0x08, 0x07, 0x01, 0x08, 0x07, 0x01, 0x08, 0x07, 0x01, 0x08 };
            byte[] keyConstantB = { 0x07, 0x08, 0x01, 0x07, 0x08, 0x01, 0x07, 0x08, 0x01, 0x07, 0x08, 0x01, 0x07, 0x08, 0x01, 0x07, 0x08, 0x01, 0x07, 0x08, 0x01, 0x07, 0x08, 0x01, 0x07, 0x08, 0x01, 0x07, 0x08, 0x01, 0x07, 0x08 };

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

        public bool DecryptData(byte[] symmetricKey, byte[] encryptedIv, String encryptedFileFilepath, String newFileFilepath)
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

            byte[] keyConstantA = { 0x01, 0x08, 0x07, 0x01, 0x08, 0x07, 0x01, 0x08, 0x07, 0x01, 0x08, 0x07, 0x01, 0x08, 0x07, 0x01, 0x08, 0x07, 0x01, 0x08, 0x07, 0x01, 0x08, 0x07, 0x01, 0x08, 0x07, 0x01, 0x08, 0x07, 0x01, 0x08 };
            byte[] keyConstantB = { 0x07, 0x08, 0x01, 0x07, 0x08, 0x01, 0x07, 0x08, 0x01, 0x07, 0x08, 0x01, 0x07, 0x08, 0x01, 0x07, 0x08, 0x01, 0x07, 0x08, 0x01, 0x07, 0x08, 0x01, 0x07, 0x08, 0x01, 0x07, 0x08, 0x01, 0x07, 0x08 };

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

        private byte[] SymmetricEncryptECB(byte[] data, byte[] aes_key)
        {
            byte[] encrypted = null;

            using (AesCryptoServiceProvider aesECB = new AesCryptoServiceProvider())
            {
                aesECB.Mode = CipherMode.ECB;
                aesECB.Padding = PaddingMode.Zeros;
                aesECB.Key = aes_key;

                // Create an encryptor to perform the stream transform.
                ICryptoTransform encryptor = aesECB.CreateEncryptor(aesECB.Key, aesECB.IV);

                // Create the streams used for encryption.
                using (MemoryStream msEncrypt = new MemoryStream())
                {
                    using (CryptoStream csEncrypt = new CryptoStream(msEncrypt, encryptor, CryptoStreamMode.Write))
                    {
                        using (StreamWriter swEncrypt = new StreamWriter(csEncrypt))
                        {
                            //Write iv to the stream.
                            string dataAsString = Convert.ToBase64String(data);
                            swEncrypt.Write(dataAsString);
                        }
                        encrypted = msEncrypt.ToArray();
                    }
                }
            }

            return encrypted;
        }

        private byte[] SymmetricDecryptECB(byte[] data, byte[] aes_key)
        {
            string decryptedIv = string.Empty;

            using (Aes aesECB = Aes.Create())
            {
                aesECB.Mode = CipherMode.ECB;
                aesECB.Padding = PaddingMode.Zeros;
                aesECB.Key = aes_key;

                // Create a decryptor to perform the stream transform.
                using (ICryptoTransform decryptor = aesECB.CreateDecryptor(aesECB.Key, aesECB.IV))
                {
                    // Create the streams used for decryption.
                    using (MemoryStream msDecrypt = new MemoryStream(data))
                    {
                        using (CryptoStream csDecrypt = new CryptoStream(msDecrypt, decryptor, CryptoStreamMode.Read))
                        {
                            using (StreamReader srDecrypt = new StreamReader(csDecrypt))
                            {

                                // Read the decrypted bytes from the decrypting stream
                                // and place them in a string.

                                decryptedIv = srDecrypt.ReadToEnd();
                            }
                        }
                    }
                }
            }

            int index = decryptedIv.LastIndexOf(@"=");
            if (index > 0)
                decryptedIv = decryptedIv.Substring(0, index + 1);

            return Convert.FromBase64String(decryptedIv);
        }

        public static byte[] HashAndSignBytes(byte[] DataToSign, RSA rsa)
        {
            try
            {
                // Hash and sign the data. Pass Pass HashAlgorithmName.SHA256
                // to specify the hashing algorithm.
                return rsa.SignData(DataToSign, System.Security.Cryptography.HashAlgorithmName.SHA256, RSASignaturePadding.Pkcs1);
            }
            catch (CryptographicException e)
            {
                Console.WriteLine(e.Message);

                return null;
            }
        }

        public static bool VerifySignedHash(byte[] DataToVerify, byte[] SignedData, RSA rsa)
        {
            try
            {
                // Verify the data using the signature.  Pass HashAlgorithmName.SHA256
                // to specify the hashing algorithm.
                return rsa.VerifyData(DataToVerify, SignedData, System.Security.Cryptography.HashAlgorithmName.SHA256, RSASignaturePadding.Pkcs1);
            }
            catch (CryptographicException e)
            {
                Console.WriteLine(e.Message);

                return false;
            }
        }


    }
}
