using Caliburn.Micro;
using SrnClient.Models;
using SrnClient.Services;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace SrnClient.ViewModels
{
    public class RegisterViewModel : Screen
    {
        private User _user;
        private PrivatePublicPair _privatePublicPair;
        private CryptoService _cryptoService;

        public RegisterViewModel()
        {
            _cryptoService = new CryptoService();
            _privatePublicPair = _cryptoService.GenerateRsaKeys();
            _user = new User();
        }

        public string Mail
        {
            get 
            { 
                return _user.Mail; 
            }
            set 
            { 
                _user.Mail = value;
                NotifyOfPropertyChange(() => Mail);
            }
        }

        public string Gender
        {
            get
            {
                return _user.Gender;
            }
            set
            {
                _user.Gender = value;
                NotifyOfPropertyChange(() => Gender);
            }
        }

        public string FirstName
        {
            get
            {
                return _user.FirstName;
            }
            set
            {
                _user.FirstName = value;
                NotifyOfPropertyChange(() => FirstName);
            }
        }

        public string LastName
        {
            get
            {
                return _user.LastName;
            }
            set
            {
                _user.LastName = value;
                NotifyOfPropertyChange(() => LastName);
            }
        }

        public string Birthday
        {
            get
            {
                return _user.BirthDay;
            }
            set
            {
                _user.BirthDay= value;
                NotifyOfPropertyChange(() => Birthday);
            }
        }

        public string Birthplace
        {
            get
            {
                return _user.Birthplace;
            }
            set
            {
                _user.Birthplace = value;
                NotifyOfPropertyChange(() => Birthplace);
            }
        }

        public string Country
        {
            get
            {
                return _user.Country;
            }
            set
            {
                _user.Country= value;
                NotifyOfPropertyChange(() => Country);
            }
        }

        public string State
        {
            get
            {
                return _user.State;
            }
            set
            {
                _user.State = value;
                NotifyOfPropertyChange(() => State);
            }
        }

        public string Street
        {
            get
            {
                return _user.Street;
            }
            set
            {
                _user.Street = value;
                NotifyOfPropertyChange(() => Street);
            }
        }

        public string Password
        {
            get
            {
                return _user.Password;
            }
            set
            {
                _user.Password = value;
                NotifyOfPropertyChange(() => Password);
            }
        }

        public string PublicKey
        {
            get
            {
                return _user.PublicKey;
            }
            set
            {
                _user.PublicKey = value;
                NotifyOfPropertyChange(() => PublicKey);
            }
        }

        public string MasterKey
        {
            get
            {
                return _user.MasterKey;
            }
            set
            {
                _user.MasterKey = value;
                NotifyOfPropertyChange(() => MasterKey);
            }
        }

        public void RegisterUser() 
        { 
        
        }
    }
}
