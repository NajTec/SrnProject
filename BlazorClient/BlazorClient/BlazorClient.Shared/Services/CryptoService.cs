using BlazorClient.Shared.Contracts;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using System.Security.Cryptography;

namespace BlazorClient.Shared.Services
{
    public class CryptoService : ICrypto
    {
        public Guid Id;

        public CryptoService()
        {

        }

        public void GenerateRSA() {
            RSACryptoServiceProvider csp = new RSACryptoServiceProvider(2048);

        }

        public static void Main() {
            CryptoService cryptoService = new CryptoService();
            cryptoService.GenerateRSA();
        }
    }
}
