using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;

namespace BlazorClient.Models
{
    public class FileDTO
    {
        public string Owner { get; set; }
        public string Filename { get; set; }
        public string Buffstring { get; set; }
        public List<string> Granted { get; set; }
    }
}
