public class User {
    private String username;
    private String password;
    private String email;
    private long phoneNumber;
    private String role;
    
        public User(){
    
        }    
        public User(String username, String password, String role, String email, long phoneNumber){
            this.username = username;
            this.password = password;
            this.email = email;
            this.phoneNumber = phoneNumber;
            this.role = role;  
        }
        //get methods
        public String getusername(){
            return username;
        }
        public String getpassword(){
            return password;
        }
        public String getrole(){
            return role;
        }     
        public String getemail(){
            return email;
        }
        public long getphoneNumber(){
            return phoneNumber;
        }
        //set methods
        public void setusername(String username){
            this.username = username;
        }
        public void setpassword(String password){
            this.password = password;
        }
        public void setrole(String role){
            this.role = role;
        }    
        public void setemail(String email){
            this.email = email;
        }
        public void setphoneNumber(long phoneNumber){
            this.phoneNumber = phoneNumber;
        }
        @Override
        public String toString(){
            return "\n" + username + "," + password + "," + role + "," + email + "," + phoneNumber;
        }
    
}