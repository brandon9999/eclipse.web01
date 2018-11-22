package kr.co.hanbitbook.ejb.examples.shop;

public class UserDataBean  implements java.io.Serializable{
        private String id;
        private String name;
        private String passwd;
        private int userlevel;

        public UserDataBean(){
          id = null;
          name = null;
          passwd = null;
          userlevel = 3;
        }

        public String getId() {
                return id;
        }


        public String getName() {
                return name;
        }


        public String getPasswd() {
                return passwd;
        }

        public int getUserlevel() {
                return userlevel;
        }

        public void setId(String string) {
                id = string;
        }

        public void setName(String string) {
                name = string;
        }

        public void setPasswd(String string) {
                passwd = string;
        }

        public void setUserlevel(int i) {
                userlevel = i;
        }

}
