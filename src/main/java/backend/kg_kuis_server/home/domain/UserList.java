package backend.kg_kuis_server.home.domain;

public enum UserList {

    김건국("김건국");

    private final String name;

    UserList(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}