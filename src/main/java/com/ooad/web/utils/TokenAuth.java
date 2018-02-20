/*
 * Created by Sandeep Tadepalli on 12/02/18 11:08
 * Copyright (c) 2018. All rights reserved.
 */

package com.ooad.web.utils;

import com.ooad.web.dao.SellerDao;
import com.ooad.web.dao.UserDao;
import com.ooad.web.model.Seller;
import com.ooad.web.model.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.impl.crypto.MacProvider;

import java.security.Key;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public abstract class TokenAuth {
    private static final Key key = MacProvider.generateKey();
    private static final SignatureAlgorithm signatureAlgo = SignatureAlgorithm.HS512;
    private static final long ttl = TimeUnit.DAYS.toMillis(48);

    public static String generateToken(User user) {
        final long nowMills = System.currentTimeMillis();
        final long expTime = nowMills + ttl;
        System.out.println();
        return Jwts.builder().setSubject(String.valueOf(user.getId()))
                .signWith(signatureAlgo, key)
                .setIssuedAt(new Date(nowMills))
                .setExpiration(new Date(expTime))
                .compact();
    }

    public static String generateSellerToken(Seller seller) {
        final long nowMills = System.currentTimeMillis();
        final long expTime = nowMills + ttl;
        System.out.println();
        return Jwts.builder().setSubject(String.valueOf(seller.getId()))
                .signWith(signatureAlgo, key)
                .setIssuedAt(new Date(nowMills))
                .setExpiration(new Date(expTime))
                .compact();
    }



    public static User getUserFromToken(String jwt) {
        try {

            Claims claims = Jwts.parser().setSigningKey(key).parseClaimsJws(jwt).getBody();
            final int userId = Integer.parseInt(claims.getSubject());
            UserDao userDao = new UserDao();
            return userDao.getUser(userId);
        } catch (SignatureException e) {
            return null;
        } catch (Exception e) {
            return null;
        }
    }

    public static Seller getSellerFromToken(String jwt) {
        try {

            Claims claims = Jwts.parser().setSigningKey(key).parseClaimsJws(jwt).getBody();
            final int sellerId = Integer.parseInt(claims.getSubject());
            SellerDao sellerDao = new SellerDao();
            return sellerDao.getSeller(sellerId);
        } catch (SignatureException e) {
            return null;
        } catch (Exception e) {
            return null;
        }
    }
}
