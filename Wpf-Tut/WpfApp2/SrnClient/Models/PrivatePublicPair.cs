using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace SrnClient.Models
{
    public class PrivatePublicPair
    {
        public System.Security.Cryptography.RSA rsa { get; set; }

        public PrivatePublicPair()
        {
            rsa = null;
        }

        public byte[] GetRSAPrivateKey()
        {
            if (rsa == null)
            {
                return null;
            }

            return rsa.ExportRSAPrivateKey();
        }

        public byte[] GetRSAPublicKey()
        {
            if (rsa == null)
            {
                return null;
            }

            return rsa.ExportRSAPublicKey();
        }
    }
}
