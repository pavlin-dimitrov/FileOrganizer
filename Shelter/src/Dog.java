public class Dog {
    private String name;
    private String profilePicture;
    private String color;
    private int egn;
    private boolean isAdopted;

    public Dog() {
        isAdopted = false;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getProfilePicture() {
        return this.profilePicture;
    }

    public void setProfilePicture(String profilePicture) {
        this.profilePicture = profilePicture;
    }

    public String getColor() {
        return this.color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public int getEgn() {
        return this.egn;
    }

    public void setEgn(int egn) {
        this.egn = egn;
    }

    public boolean isAdopted() {
        return this.isAdopted;
    }

    public boolean setAdopted() {
        isAdopted = true;
        return isAdopted;
    }
}
