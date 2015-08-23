import java.util.Date;

public class User {
      private int userId;
      private String firstName;
      private String lastName;
      private String email;

      public void setUserId(int userId) {
          this.userId = userId;
      }

      public String getFirstName() {
          return firstName;
      }

      public void setFirstName(String firstName) {
          this.firstName = firstName;
      }

      public String getLastName() {
          return lastName;
      }

      public void setLastName() {
          this.lastName = lastName;
      }

      public String getEmail {
         return email;
      }

      public void setEmail(String email) {
          this.email = email;
      }

      @Override
      public String toString() {
          return "User: " + userId + ", First Name: " + firstName +
            ", Last Name: " + lastName + ", Email: " + email;
      }

}
