using BlazorClient.Contracts;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using System.Security.Cryptography;

namespace BlazorClient.Services
{
    public class CryptoService : ICrypto
    {
        public Guid Id;



        public CryptoService()
        {

        }

        public static void Main() {
        }
    }
}
