package kr.co.hanbitbook.ejb.examples.shop;

public class ItemDataBean  implements java.io.Serializable{
        private Integer seq;
        private String id;
        private String categoryname;
        private String name;
        private int price;
        private String text;
        private int count;
        private java.sql.Timestamp regdate;

        public Integer getSeq(){
          return seq;
        }
        public int getCount(){
          return count;
        }
        public void setSeq(Integer seq){
          this.seq = seq;
        }
        public void setCount(int count){
          this.count = count;
        }

        public String getCategoryname() {
                return categoryname;
        }

        public String getId() {
                return id;
        }

        public String getName() {
                return name;
        }

        public int getPrice() {
                return price;
        }

        public java.sql.Timestamp getRegdate() {
                return regdate;
        }

        public String getText() {
                return text;
        }

        public void setCategoryname(String string) {
                categoryname = string;
        }

        public void setId(String string) {
                id = string;
        }

        public void setName(String string) {
                name = string;
        }

        public void setPrice(int i) {
                price = i;
        }

        public void setRegdate(java.sql.Timestamp timestamp) {
                regdate = timestamp;
        }

        public void setText(String string) {
                text = string;
        }

        public String getRegdateString(){
          java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm");
          String regdateString = sdf.format(regdate);
          return regdateString;
        }

        public java.sql.Timestamp getCurrentTimestamp(){
          long time = new java.util.Date().getTime();
          java.sql.Timestamp timeStamp = new java.sql.Timestamp(time);
          return timeStamp;
        }

}
