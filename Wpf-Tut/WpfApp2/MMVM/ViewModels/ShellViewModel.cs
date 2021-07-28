using Caliburn.Micro;
using MMVM.Models;
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
        private string _lastName;
        private Person _selectedPerson;
        private BindableCollection<Person> _people = new BindableCollection<Person>();

        public ShellViewModel()
        {
            People.Add(new Person { FirstName = "Jan", LastName = "Klotter" });
            People.Add(new Person { FirstName = "Susi", LastName = "Sinnlos" });
            People.Add(new Person { FirstName = "Max", LastName = "Max Mustermann" });
        }
        public string FirstName
        {
            get 
            { 
                return _firstname; 
            }
            set 
            {
                _firstname = value;
                NotifyOfPropertyChange(() => FirstName);
                NotifyOfPropertyChange(() => FullName);
            }
        }
        public string LastName
        {
            get
            {
                return _lastName;
            }
            set
            {
                _lastName = value;
                NotifyOfPropertyChange(() => LastName);
                NotifyOfPropertyChange(() => FullName);
            }
        }
        public string FullName
        {
            get 
            { 
                return $"{ FirstName } { LastName }"; 
            }
        }
        public BindableCollection<Person> People 
        {
            get { return _people; }
            set { _people = value; } 
        }
        public Person SelectedPerson
        {
            get { return _selectedPerson; }
            set 
            { 
                _selectedPerson = value;
                NotifyOfPropertyChange(() => SelectedPerson);
            }
        }

        public bool CanClearText(string firstname, string lastname) {
            return !String.IsNullOrWhiteSpace(firstname) || !String.IsNullOrWhiteSpace(lastname);
        }
        
        public void ClearText(string firstname, string lastname)
        {
            FirstName = string.Empty;
            LastName = string.Empty;
        }


    }
}
