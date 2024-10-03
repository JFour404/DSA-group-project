import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class SocialNetwork {
    private List<Person> peopleNetwork = new ArrayList<>();
    private List<Friendship> relationships = new ArrayList<>(); 

    // Method to add a person to the network
    public void addPerson(Person person) {
        if (findPersonById(person.getIdentifier()) == null) {
            peopleNetwork.add(person);
            System.out.println(person.getName() + " added to the network.");
        } else {
            System.out.println("Person with ID " + person.getIdentifier() + " already exists.");
        }
    }
    // Search for a person by their identifier
    public void searchPerson(String id) {
        Person person = findPersonById(id);
        if (person != null) {
            System.out.println("Found person: " + person.getName() + " " + person.getSurname() + ", ID: " + person.getIdentifier());
        } else {
            System.out.println("Person with ID " + id + " not found.");
        }
    }
    // Method to find a person by their identifier
    private Person findPersonById(String id) {
        for (Person person : peopleNetwork) {
            if (person.getIdentifier().equals(id)) {
                return person;
            }
        }
        return null;
    }
    // Method to load people data from a file
    public void loadPeopleData(String filename) {
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] personData = line.split(","); 
                String identifier = personData[0]; 

                // Create a new Person object with the data, handling missing fields
                Person person = new Person(
                    identifier, 
                    personData.length > 1 ? personData[1] : "", 
                    personData.length > 2 ? personData[2] : "", 
                    personData.length > 3 ? personData[3] : "", 
                    personData.length > 4 ? personData[4] : "",
                    personData.length > 5 ? personData[5] : "", 
                    personData.length > 6 ? personData[6] : "", 
                    new ArrayList<>(), // StudiedAt
                    new ArrayList<>(), // WorkedAt
                    new ArrayList<>(), // Movies
                    personData.length > 7 ? personData[7] : ""  // GroupCode
                );

                // Add the person to the network
                addPerson(person);
            }
        } catch (IOException e) {
            System.err.println("Error reading file: " + e.getMessage());
        }
    }

    // Method to load friendships from the file
    public void loadRelationships(String filename) {
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] friends = line.split(",");
                if (friends.length == 2) {
                    String friend1 = friends[0].trim();
                    String friend2 = friends[1].trim();

                    // Add the relationship in both directions
                    addFriendship(friend1, friend2);
                    addFriendship(friend2, friend1);
                }
            }
            System.out.println("Friendships loaded successfully from " + filename);
        } catch (IOException e) {
            System.err.println("Error reading file: " + e.getMessage());
        }
    }

    // Print out all people in the network
    public void printPeopleToFile() {
        if (peopleNetwork.isEmpty()) {
            System.out.println("No people in the network.");
        } else {
            try (BufferedWriter writer = new BufferedWriter(new FileWriter("people_output.txt"))) {
                for (Person person : peopleNetwork) {
                    writer.write(person.getIdentifier() + ": " + person.getName() + " " + person.getSurname());
                    writer.newLine(); 
                }
                System.out.println("People have been written to people_output.txt.");
            } catch (IOException e) {
                System.err.println("Error writing to file: " + e.getMessage());
            }
        }
    }

    private void addFriendship(String personId, String friendId) {
        if (findPersonById(personId) != null && findPersonById(friendId) != null) {
            relationships.add(new Friendship(personId, friendId));
        } else {
            System.out.println("One or both of the persons (" + personId + ", " + friendId + ") do not exist in the network.");
        }
    }

    // Print friendship relation
    public void printFriendships(String personId) {
        Person person = findPersonById(personId);
        if (person != null) {
            List<String> friends = getFriends(personId);
            if (friends.isEmpty()) {
                System.out.println(personId + " has no friends in the network.");
            } else {
                System.out.println(personId + " is friends with: " + String.join(", ", friends));
            }
        } else {
            System.out.println("Person with ID " + personId + " not found in the network.");
        }
    }

    // Method to get a person's friends
    private List<String> getFriends(String personId) {
        List<String> friends = new ArrayList<>();
        for (Friendship friendship : relationships) {
            if (friendship.getPerson1().equals(personId)) {
                friends.add(friendship.getPerson2());
            }
        }
        return friends;
    }

    // Method to delete a person
    public void deletePerson(String personId) {
        // Check if the person exists in the network
        Person personToDelete = findPersonById(personId);
        if (personToDelete == null) {
            System.out.println("Person with ID " + personId + " does not exist in the network.");
            return;
        }
    
        // Remove the person from the people network
        peopleNetwork.remove(personToDelete);
    
        // Remove all friendships involving this person
        relationships.removeIf(friendship -> 
            friendship.getPerson1().equals(personId) || friendship.getPerson2().equals(personId)
        );
    
        // Remove the person's ID from other people's friend lists
        for (Friendship friendship : new ArrayList<>(relationships)) {
            if (friendship.getPerson1().equals(personId) || friendship.getPerson2().equals(personId)) {
                continue;
            }
            // If the friendship involves the deleted person, remove it from the corresponding friend's list
            if (friendship.getPerson1().equals(personId)) {
                relationships.remove(friendship);
            } else if (friendship.getPerson2().equals(personId)) {
                relationships.remove(friendship);
            }
        }
    
        System.out.println("Person with ID " + personId + " and their relationships have been removed.");
    }
    
}
