using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace SrnClient.Models
{
    public class User
    { 
        public string Mail{ get; set; }
        public string Gender { get; set; }
        public string FirstName { get; set; }
        public string LastName { get; set; }
        public string BirthDay{ get; set; }
        public string Birthplace { get; set; }
        public string Country { get; set; }
        public string State { get; set; }
        public string Street { get; set; }
        public string Password { get; set; }
        public string PublicKey { get; set; }
        public string MasterKey { get; set; }
    }
}
