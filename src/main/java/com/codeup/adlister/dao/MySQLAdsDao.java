package com.codeup.adlister.dao;

import com.codeup.adlister.models.Ad;
import com.codeup.adlister.models.User;
import com.mysql.cj.jdbc.Driver;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MySQLAdsDao implements Ads {
    private Connection connection = null;

    public MySQLAdsDao(Config config) {
        try {
            DriverManager.registerDriver(new Driver());
            connection = DriverManager.getConnection(
                    config.getUrl(),
                    config.getUser(),
                    config.getPassword()
            );
        } catch (SQLException e) {
            throw new RuntimeException("Error connecting to the database!", e);
        }
    }

    @Override
    public List<Ad> all() {
        PreparedStatement stmt = null;
        try {
            stmt = connection.prepareStatement("SELECT * FROM ads");
            ResultSet rs = stmt.executeQuery();
            return createAdsFromResults(rs);
        } catch (SQLException e) {
            throw new RuntimeException("Error retrieving all ads.", e);
        }
    }

    @Override
    public Long insert(Ad ad) {
        try {
            String insertQuery = "INSERT INTO ads(user_id, title, description, image_url) VALUES (?, ?, ?, ?)";
            PreparedStatement stmt = connection.prepareStatement(insertQuery, Statement.RETURN_GENERATED_KEYS);
            stmt.setLong(1, ad.getUserId());
            stmt.setString(2, ad.getTitle());
            stmt.setString(3, ad.getDescription());
            stmt.setString(4, ad.getImageUrl());
            stmt.executeUpdate();
            ResultSet rs = stmt.getGeneratedKeys();
            rs.next();
            return rs.getLong(1);
        } catch (SQLException e) {
            throw new RuntimeException("Error creating a new ad.", e);
        }
    }

    private Ad extractAd(ResultSet rs) throws SQLException {
        return new Ad(

                rs.getLong("id"),
                rs.getLong("user_id"),
                rs.getString("title"),
                rs.getString("description"),
                rs.getString("image_url")
        );
    }

    private List<Ad> createAdsFromResults(ResultSet rs) throws SQLException {
        List<Ad> ads = new ArrayList<>();
        while (rs.next()) {
            ads.add(extractAd(rs));
        }
        return ads;
    }

    @Override
    public List<Ad> userAds(User user) {
        PreparedStatement stmt = null;
        try {
            stmt = connection.prepareStatement("SELECT * FROM ads WHERE user_id = ?");
            stmt.setLong(1, user.getId());
            ResultSet rs = stmt.executeQuery();
            return createAdsFromResults(rs);
        } catch (SQLException e) {
            throw new RuntimeException("Error retrieving user Ads");
        }
    }

    @Override
    public Ad getAdById(long id) {
        Ad ad = null;
        try {
            //"AND is_Deleted=0" how to check if it is deleted on 101
            PreparedStatement stmt = connection.prepareStatement("SELECT * FROM ADS WHERE id=?");
            stmt.setLong(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                ad = new Ad(
                        rs.getLong("id"),
                        rs.getLong("user_id"),
                        rs.getString("title"),
                        rs.getString("description"),
                        rs.getString("image_url")
                );
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error getting Ad id", e);
        }
        return ad;
    }

    @Override
    public void deleteAd(Ad ad) {

    }

    public Long delete(Long adId) {
        try {
            String deleteQuery = "DELETE FROM ads WHERE id = ?";
            PreparedStatement ps = connection.prepareStatement(deleteQuery);
            ps.setLong(1, adId);
            ps.executeUpdate();
            return Long.valueOf(2);
        } catch (SQLException e) {
            throw new RuntimeException("Error deleting ad", e);
        }
    }

    @Override
    public Ad singleAd(Long adId) {
        PreparedStatement stmt = null;

        try {
            stmt = connection.prepareStatement("SELECT ads.*, users.username FROM ads JOIN users ON users.id = ads.user_id WHERE ads.id = ?");
            stmt.setLong(1, adId);
            ResultSet rs = stmt.executeQuery();
            rs.next();

            return extractAd(rs);


        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Error finding ad.", e);
        }

    }

    @Override
    public Long updateAd(Ad ad) {
        String query = "UPDATE ads SET title = ?, description = ?, image_url = ? WHERE id = ?";
        try {
            PreparedStatement stmt = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            stmt.setString(1, ad.getTitle());
            stmt.setString(2, ad.getDescription());
            stmt.setString(3, ad.getImageUrl());
            stmt.setLong(4, ad.getId());
            stmt.executeUpdate();
            ResultSet rs = stmt.getGeneratedKeys();
            rs.next();
            rs.getLong(1);
        } catch (SQLException e) {
            throw new RuntimeException("Error editing ad", e);
        }
        return null;
    }

  
  @Override
    public Long linkAdToCategory(long adId, long categoryId) {
        String query = "INSERT INTO ad_category (ad_id, category_id) VALUES (?, ?)";
        try {
            PreparedStatement stmt = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            stmt.setLong(1, adId);
            stmt.setLong(2, categoryId);
            return (long) stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Error linking ad to category", e);
        }
    }

    @Override
    public List<Ad> getAdsWithCategory(Long categoryId) {
        List<Ad> ads = new ArrayList<>();
        String query = "SELECT * FROM ads WHERE id IN ( " +
                "SELECT ad_id " +
                "FROM ad_category " +
                "WHERE category_id = ? )";
        try {
            PreparedStatement stmt = connection.prepareStatement(query);
            stmt.setLong(1, categoryId);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                ads.add(new Ad (
                        rs.getLong("id"),
                        rs.getLong("user_id"),
                        rs.getString("title"),
                        rs.getString("description"),
                        rs.getString("image_url")
                ));
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error retrieving ads from category", e);
        }
        return ads;
    }

    @Override
    public List<Ad> getAdsByTitle(String title) {
        String query = "SELECT * FROM ads WHERE title LIKE ?";
        String titleSearch = "%" + title + "%";
        try {
            PreparedStatement stmt = connection.prepareStatement(query);
            stmt.setString(1, titleSearch);
            ResultSet rs = stmt.executeQuery();
            return createAdsFromResults(rs);
        } catch (SQLException e) {
            throw new RuntimeException("Error finding ad by title", e);
        }
    }

    @Override
    public List<Ad> getAdsByDescription(String description) {
        String query = "SELECT * FROM ads WHERE description LIKE ?";
        String descriptionSearch = "%" + description + "%";
        try {
            PreparedStatement stmt = connection.prepareStatement(query);
            stmt.setString(1, descriptionSearch);
            ResultSet rs = stmt.executeQuery();
            return createAdsFromResults(rs);
        } catch (SQLException e) {
            throw new RuntimeException("Error finding ad by description", e);
        }

    }


}

