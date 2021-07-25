using BlazorClientNew.Contracts;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using System.Security.Cryptography;
using System.Net.Http;
using BlazorClientNew.Models;
using Newtonsoft.Json;
using System.Net.Mime;
using System.IO;

namespace BlazorClientNew.SingeltonInstances
{
    public class Client
    {
        // Client kapselt PublicPrivateKey
        public PrivatePublicPair PublicPrivateKeyPair { get; set; }
        public byte[][] SymmetricKeyAndIV { get; set; }
        public UserDTO UserDTO { get; set; }
        public List<byte[]> EncryptedFiles { get; set; }
        public List<byte[]> Signature { get; set; }

        public Client()
        { 
        
        }

        public Task GetCustomer()
        {
            throw new NotImplementedException();
        }

        /*
        //Post: Customer
        public async Task RegisterCustomer(UserDTO userDTO)
        {
            this.UserDTO = userDTO;
            Console.WriteLine("Im RegisterCustomer");
            PayloadDTO payloadDTO = new PayloadDTO();
            string publicKey = "123412451235123";
            userDTO._publickey = publicKey;
            string data = JsonConvert.SerializeObject(userDTO);
            Console.WriteLine(data);
            HttpClient.BaseAddress = new Uri("http://localhost:8080");
            payloadDTO.Payload = data;
            HttpResponseMessage response = await HttpClient.PostAsync("User", new StringContent(content: data, encoding: System.Text.Encoding.UTF8, mediaType: MediaTypeNames.Application.Json));
            Console.WriteLine(response.StatusCode);
            throw new NotImplementedException();
        }*/

        public Task UploadFile()
        {
            throw new NotImplementedException();
        }

    }
}
