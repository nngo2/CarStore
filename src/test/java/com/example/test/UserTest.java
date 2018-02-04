package com.example.test;

import com.example.util.HashHelper;
import org.junit.Test;
import org.junit.Assert;

public class UserTest  {
    @Test
    public void testHashPassword() {
        String pass = HashHelper.hash("test");
        System.out.println(pass);
        System.out.println(HashHelper.hash("test1"));
        System.out.println(HashHelper.hash("test2"));
        Assert.assertNotNull(pass);
    }
}
