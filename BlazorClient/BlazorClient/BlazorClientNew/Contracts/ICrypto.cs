using System;
using System.Collections.Generic;
using System.Linq;
using System.Security.Cryptography;
using System.Threading.Tasks;

namespace BlazorClientNew.Contracts
{
    public interface ICrypto
    {
        public RSA GenerateRSA();
        public string[] GenerateRSA2();
        public byte[][] GenerateAesKeyAndIv();
        public byte[][] EncryptAesKeyAndIv(byte[] AesKey, byte[] IV, RSA rsa);
        public byte[][] DecryptAesKeyAndIv(byte[] encryptedSymmetricKey, byte[] encryptedSymmetricIV, RSA rsa);
        public byte[] EncryptData(byte[] symmetricKey, byte[] iv, String filepath);
        public bool DecryptData(byte[] symmetricKey, byte[] encryptedIv, String encryptedFileFilepath, String newFileFilepath);
        public byte[] EncryptKeyWithPassword(byte[] key, string password);
        public byte[] DecryptKeyWithPassword(byte[] key, string password);
    }
}
