using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;

namespace BlazorClient.Models
{
    public class UserDTO
    {
        public string _email { get; set; }
        public string _password { get; set; }
        public string _gender { get; set; }
        public string _firstname { get; set; }
        public string _lastname { get; set; }
        public string _birthday { get; set; }
        public string _country { get; set; }
        public string _state { get; set; }
        public string _activity { get; set; }
        public string _license { get; set; }
        public string _session { get; set; }
        public List<byte[]> _encryptedFiles { get; set; }
        public List<byte[]> _signature { get; set; }

        // Dieses Objekt wird als Json zu einem String gemacht und mit einem PublicKey des Servers verschlüsselt
        // Post: {payload: "verschlüsslesteJsonString"}
    }
}
