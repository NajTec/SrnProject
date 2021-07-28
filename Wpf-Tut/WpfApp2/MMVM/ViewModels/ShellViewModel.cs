using Caliburn.Micro;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace MMVM.ViewModels
{
    public class ShellViewModel : Screen
    {
        private string _firstname = "Jan";

        public string FirstName
        {
            get 
            { 
                return _firstname; 
            }
            set 
            {
                _firstname = value; 
            }
        }

    }
}
