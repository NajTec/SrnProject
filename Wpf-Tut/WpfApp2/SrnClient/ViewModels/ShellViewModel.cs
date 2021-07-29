using Caliburn.Micro;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace SrnClient.ViewModels
{
    public class ShellViewModel : Conductor<object>
    {
        private string _userName = "Jan";
        public ShellViewModel()
        {

        }
        public string UserName
        {
            get 
            { 
                return _userName; 
            }
            set 
            { 
                _userName = value; 
            }
        }

        public void LoadLogin()
        {
            ActivateItemAsync(new LoginViewModel());
        }

        public void LoadRegister()
        {
            ActivateItemAsync(new RegisterViewModel());
        }
    }
}
