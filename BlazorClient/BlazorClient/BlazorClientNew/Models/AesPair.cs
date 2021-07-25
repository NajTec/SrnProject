using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;

namespace BlazorClientNew.Models
{
    public class AesPair
    {
        public byte[] Key { get; set; }
        public byte[] IV { get; set; }
    }
}
