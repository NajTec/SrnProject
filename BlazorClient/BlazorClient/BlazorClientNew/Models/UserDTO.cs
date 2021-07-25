using Newtonsoft.Json;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;

namespace BlazorClientNew.Models
{
    public class UserDTO
    {
        [JsonProperty("email")]
        public string _email { get; set; }
        [JsonProperty("password")]
        public string _password { get; set; }
        [JsonProperty("gender")]
        public string _gender { get; set; }
        [JsonProperty("firstname")]
        public string _firstname { get; set; }
        [JsonProperty("lastname")]
        public string _lastname { get; set; }
        [JsonProperty("birthday")]
        public string _birthday { get; set; }
        [JsonProperty("country")]
        public string _country { get; set; }
        [JsonProperty("state")]
        public string _state { get; set; }
        [JsonProperty("street")]
        public string _street { get; set; }
        [JsonProperty("publickey")]
        public string _publickey { get; set; }
        [JsonProperty("masterkey")]
        public string _masterkey { get; set; }
        [JsonProperty("session")]
        public string _session { get; set; }
        
    }
}
