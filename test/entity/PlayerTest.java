package entity;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;


public class PlayerTest {
    private Player player;
    private List<Role> roles;

    @Before
    public void setUp() {
        roles = new ArrayList<>();
        player = new Player("testPlayer", "password123", roles);
    }

    @Test
    public void testConstructor() {
        assertEquals("testPlayer", player.getName());
        assertEquals("password123", player.getPassword());
        assertEquals(0, player.getScore());
        assertEquals(roles, player.getRoles());
    }

    @Test
    public void testGetName() {
        assertEquals("testPlayer", player.getName());
    }

    @Test
    public void testSetName() {
        player.setName("newName");
        assertEquals("newName", player.getName());
    }

    @Test
    public void testGetScore() {
        assertEquals(0, player.getScore());
    }

    @Test
    public void testSetScore() {
        player.setScore(10);
        assertEquals(10, player.getScore());
    }

    // getPassword method should not be tested as it is a security risk to expose password validation in unit tests

    @Test
    public void testGetRoles() {
        assertEquals(roles, player.getRoles());
    }

    @Test
    public void testSetRoles() {
        // Create a mock Role
        Role mockRole1 = Mockito.mock(Role.class);
        Role mockRole2 = Mockito.mock(Role.class);

        // Create a list of mock Roles
        List<Role> roles = Arrays.asList(mockRole1, mockRole2);

        // Create a Player instance
        Player player = new Player("username", "password", roles);

        // Set the mock roles to the player
        player.setRoles(roles);

        // Assertions to verify the roles were set correctly
        assertSame("Roles should be the same as what was set", roles, player.getRoles());
    }

    @Test
    public void testToString() {
        String expectedString = "Player{ name='testPlayer', score=0, roles=" + roles + '}';
        assertEquals(expectedString, player.toString());
    }
}