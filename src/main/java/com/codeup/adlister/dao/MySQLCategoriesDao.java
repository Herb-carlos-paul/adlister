package com.codeup.adlister.dao;

import com.codeup.adlister.models.Category;
import com.mysql.cj.jdbc.Driver;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MySQLCategoriesDao implements Categories {
    private Connection connection = null;

    public MySQLCategoriesDao(Config config) {
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
    public List<Category> all() {
        PreparedStatement stmt = null;
        try {
            stmt = connection.prepareStatement("SELECT * FROM categories");
            ResultSet rs = stmt.executeQuery();
            return createCategoriesFromResult(rs);
        } catch (SQLException e) {
            throw new RuntimeException("Error retrieving all categories.", e);
        }
    }

    @Override
    public List<Category> getCategoriesLinkedWithAd(Long adId) {
        List<Category>categories = new ArrayList<>();
        String query = "SELECT * " +
                "FROM categories " +
                "WHERE id IN (" +
                "SELECT category_id " +
                "FROM ad_category " +
                "WHERE ad_id = ? )";
        try{
            PreparedStatement stmt = connection.prepareStatement(query);
            stmt.setLong(1, adId);
            ResultSet rs = stmt.executeQuery();
            while(rs.next()) {
                categories.add(new Category(rs.getString("name")));
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error retrieving associated categories from ad.", e);
        }
        return categories;
    }

    private Category extractCategory(ResultSet rs) throws SQLException {
        return new Category(
                rs.getLong("id"),
                rs.getString("name")
        );
    }

    private List<Category> createCategoriesFromResult(ResultSet rs) throws SQLException {
        List<Category> categories = new ArrayList<>();
        while (rs.next()) {
            categories.add(extractCategory(rs));
        }
        return categories;
    }

    public Category getCategoryById(long id) {
        Category category = null;
        try {
            PreparedStatement stmt = connection.prepareStatement("SELECT * FROM categories WHERE id=?");
            stmt.setLong(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                category = new Category(
                        rs.getLong("id"),
                        rs.getString("name")
                );
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error getting Category", e);
        }
        return category;
    }
}
