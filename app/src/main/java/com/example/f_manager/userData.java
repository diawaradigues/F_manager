package com.example.f_manager;

public class userData {

    public Dataclass getData() {
        return data;
    }

    public void setData(Dataclass data) {
        this.data = data;
    }

    public support_class getSupport() {
        return support;
    }

    public void setSupport(support_class support) {
        this.support = support;
    }

    Dataclass data;
    support_class support;
    class Dataclass{
        public String getFirst_name() {
            return first_name;
        }

        public void setFirst_name(String first_name) {
            this.first_name = first_name;
        }

        public String getLast_name() {
            return last_name;
        }

        public void setLast_name(String last_name) {
            this.last_name = last_name;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getAvatar() {
            return avatar;
        }

        public void setAvatar(String avatar) {
            this.avatar = avatar;
        }

        String first_name,last_name,id,email,avatar;
    }
    class support_class{
        String Url,textr;

        public String getUrl() {
            return Url;
        }

        public void setUrl(String url) {
            Url = url;
        }

        public String getTextr() {
            return textr;
        }

        public void setTextr(String textr) {
            this.textr = textr;
        }
    }
}
