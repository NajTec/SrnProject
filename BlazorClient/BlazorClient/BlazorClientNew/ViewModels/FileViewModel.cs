using BlazorClientNew.Contracts;
using BlazorClientNew.Models;
using BlazorClientNew.SingeltonInstances;
using Blazored.LocalStorage;
using System;
using System.Collections.Generic;
using System.IO;
using System.Linq;
using System.Threading.Tasks;

namespace BlazorClientNew.ViewModels
{
    public class FileViewModel
    {

        private ICrypto _crypto { get; }
        private ILocalStorageService _localStorageService { get; }
        public FileViewModel( ICrypto crypto, ILocalStorageService localStorageService)
        {
            _crypto = crypto;
            _localStorageService = localStorageService;
        }

        public async Task SendFile()
        {
            Client client = await _localStorageService.GetItemAsync<Client>("client");
            string privateKeyPath = ".\\private.txt";
            string publicKeyPath = ".\\public.txt";
            AesPair aesPair = new AesPair();
            byte[][] aes = _crypto.GenerateAesKeyAndIv();
            aes = _crypto.EncryptAesKeyAndIv(aes[0], aes[1], rsa: client.PublicPrivateKeyPair.rsa);
            aesPair.Key = aes[0];
            aesPair.IV = aes[1];

            using (StreamWriter sw = System.IO.File.CreateText(privateKeyPath))
            {
                sw.Write(aesPair.Key);
            }
        }
    }
}
