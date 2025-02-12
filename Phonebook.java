    public class Phonebook
    {
        // Storage of contacts.
        private Person[] contacts;
        // Number of contacts present in the phonebook.
        private int size;

        /**
         * Create a phonebook of size 50.
         */
        public Phonebook()
        {
            contacts = new Person[50];
        }

        /**
         * @return Number of contacts stored in this phonebook.
         */
        public int getSize()
        {
            // Complete this method
            return this.size;
        }

        /**
         * Get the contact at index.
         * 
         * @param index Index to get contact.
         * @return Person object from index. Null if index is not valid or out of range.
         */
        public Person getContactAtIndex(int index)
        {
            if (index >= 0 && index < this.size) {
                return contacts[index];  // Returns the contact at the given index
            }
            return null;
        }

        /**
         * Get the person object based on a given id.
         * 
         * @param id Target id.
         * @return Person object that has this id. Null if it does not exist.
         */
        public Person getContact(String id)
        {
            for (int i = 0; i < size; i++) {
                if (contacts[i].getId().equals(id)) {
                    return contacts[i];  // Returns the person object if the ID matches
                }
            }
            return null;
        }

        /**
         * Checks if this phonebook has contacts or not.
         * 
         * @return True or False.
         */
        public boolean isEmpty()
        {
            return this.getSize() == 0;
        }

        /**
         * Increase number of contacts present in this phonebook.
         */
        public void incrSize()
        {
            this.size++;
        }

        /**
         * Decrease number of contacts present in this phonebook.
         */
        public void decrSize()
        {
            this.size--;
        }

        /**
         * Increases the size of the phonebook whenever it is full.
         */
        private void increasePhonebookMaxSize()
        {
            // Double the size of the array
            Person[] newContacts = new Person[contacts.length * 2];
            
            // Copy the old contacts to the new array
            for (int i = 0; i < size; i++) {
                newContacts[i] = contacts[i];
            }
            
            contacts = newContacts;
        }

        /**
         * Inserts a new person object at its appropriate lexicographic location in the phonebook.
         * 
         * @param p Person to be addded to the Phonebook.
         */
        public void insert(Person p)
        {
            if (size == contacts.length) {
                increasePhonebookMaxSize();  // Increase the size if the phonebook is full
            }
        
            int index = findIndexInsertion(p);  // Find the appropriate index to insert
        
            // Shift existing contacts to make space for the new one
            for (int i = size; i > index; i--) {
                contacts[i] = contacts[i - 1];
            }
        
            contacts[index] = p;  // Insert the person at the found index
            incrSize();  // Increase the size of the phonebook
        }

        /**
         * Searches in what index should this person object with the given be inserted.
         * 
         * @param p Person object to be inserted into the phonebook.
         * @return Appropriate index (position).
         */
        private int findIndexInsertion(Person p)
        {
            int index = 0;
            while (index < size && contacts[index].compareTo(p) < 0) {
                index++;  // Find the index where this person should be inserted based on lexicographic order of full names
            }
            return index;  // Return the appropriate index
        }

        /**
         * Delete a person based on their contact id.
         * 
         * @param id Contact ID of that contact.
         * @return Deleted contact.
         */
        public Person deleteContact(String id)
        {
            for (int i = 0; i < size; i++) {
                if (contacts[i].getId().equals(id)) {
                    Person deletedPerson = contacts[i];  // Store the deleted contact
                    // Shift all contacts to the left to fill the gap
                    for (int j = i; j < size - 1; j++) {
                        contacts[j] = contacts[j + 1];
                    }
                    contacts[size - 1] = null;  // Nullify the last contact
                    decrSize();  // Decrease the size of the phonebook
                    return deletedPerson;  // Return the deleted contact
                }
            }
            return null;  // Return null if no contact with that ID is found
        }

        /**
         * Adjusts the existing contacts in a phonebook from a given starting index to where it ends,
         * following a particular direction.
         * 
         * @param start Index to start adjustment from.
         * @param end Index to end adjustment into.
         * @param direction Direction in which the adjustment must be made. direction = "f" if element
         *        at index 0 takes the value of the element next to it (e.g. index 1). direction = "b"
         *        if element at index 1 takes the value of the element behind it (e.g. index 0).
         */
        private void adjustPhonebook(int start, int end, String direction)
        {
            if (direction.equals("f")) {
                // Shift contacts forward (left)
                for (int i = start; i < end; i++) {
                    contacts[i] = contacts[i + 1];
                }
            } else if (direction.equals("b")) {
                // Shift contacts backward (right)
                for (int i = end; i > start; i--) {
                    contacts[i] = contacts[i - 1];
                }
            }
        }

        /**
         * Uses ellipsis to ambiguously accept as many country codes as possible. <br>
         * <br>
         * For example: <br>
         * <br>
         * If we have: printContactsFromCountryCodes(1, 2, 3) <br>
         * <br>
         * Then we get: countryCodes = { 1, 2, 3 };
         * 
         * @param countryCodes Area codes to be used as a filter.
         * @return Contacts on this phonebook under a particular area code set by the user.
         */
        public String printContactsFromCountryCodes(int... countryCodes)
        {
            StringBuilder result = new StringBuilder();

            for (int i = 0; i < size; i++) {
                Person p = contacts[i];

                for (int j = 0; j < countryCodes.length; j++) {
                    if (p.getCountryCode() == countryCodes[j]) {
                        result.append(p.toString()).append("\n");
                        break; // Stop checking once a match is found
                    }
                }
            }
            return result.length() > 0 ? result.toString() : "No contacts found with the specified country codes.";
        }

        /**
         * Print the entire phonebook without any filter or so...
         * 
         * @return The entire list of contacts present in this phonebook.
         */
        public String toString()
        {
            StringBuilder result = new StringBuilder();
            for (int i = 0; i < size; i++) {
                result.append(contacts[i].toString()).append("\n");  // Append the details of each contact in the phonebook
            }
            return result.toString();
        }
    }
