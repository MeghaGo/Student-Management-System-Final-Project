package com.example.megha.model;


import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.Collection;


@Entity //this is used to make this entity jp entity
@Table(name = "user", uniqueConstraints = @UniqueConstraint(columnNames ="email"))
//uniqueConstraints specifies that the column email should be unique for each user
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "first_name") //this is the name on table by default it is same to the attribute
    @NotEmpty(message="first_name cannot be empty")
    private String firstName;

    @Column(name = "last_name")
    @NotEmpty(message="last_name cannot be empty")
    private String lastName;
    @NotEmpty(message="email cannot be empty")
    private String email;
    @NotEmpty(message="password cannot be empty")
    private String password;

    @Column(columnDefinition = "boolean default false" )
    private boolean admin;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(
            name = "users_roles",
            joinColumns = @JoinColumn(
                    name = "user_id", referencedColumnName = "id"
            ),
            inverseJoinColumns = @JoinColumn(
                    name = "role_id", referencedColumnName = "id"))

    private Collection<Role> roles;

    public User() {
    }

    public User(String firstName, String lastName, String email, String password, boolean admin, Collection<Role> roles) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.roles = roles;
        this.admin = admin;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isAdmin() {
        return admin;
    }

    public void setAdmin(boolean admin) {
        this.admin = admin;
    }

    public Collection<Role> getRoles() {
        return roles;
    }

    public void setRoles(Collection<Role> roles) {
        this.roles = roles;
    }
}