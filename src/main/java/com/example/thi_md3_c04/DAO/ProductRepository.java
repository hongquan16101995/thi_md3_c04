package com.example.thi_md3_c04.DAO;

import com.example.thi_md3_c04.model.Category;
import com.example.thi_md3_c04.model.Product;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ProductRepository {
    private final String SELECT_ALL_PRODUCTS = "select * from product;";
    private final String SELECT_CATEGORY_BY_ID = "select * from category where id = ?;";
    private final String SELECT_PRODUCT_BY_ID = "select * from product where id = ?;";
    private final String SELECT_ALL_CATEGORY = "select * from category;";
    private final String CREATE_PRODUCT = "insert into product(name, price, quantity, color, category_id)" +
            "value (?,?,?,?,?);";
    private final String DELETE_PRODUCT_BY_ID = "delete from product where id = ?;";
    private final String UPDATE_PRODUCT_BY_ID = "update product set name = ?, price = ?, quantity = ?," +
            "color = ?, category_id = ? where id = ?;";

    private final String SEARCH_BY_NAME = "select * from product where name like ?;";
    public void createProduct(Product product) {
        try(Connection connection = new MyConnection().getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(CREATE_PRODUCT);
            preparedStatement.setString(1, product.getName());
            preparedStatement.setDouble(2, product.getPrice());
            preparedStatement.setInt(3, product.getQuantity());
            preparedStatement.setString(4, product.getColor());
            preparedStatement.setInt(5, product.getCategory().getId());
            preparedStatement.executeUpdate();
            preparedStatement.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void updateProduct(Product product) {
        try(Connection connection = new MyConnection().getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_PRODUCT_BY_ID);
            preparedStatement.setString(1, product.getName());
            preparedStatement.setDouble(2, product.getPrice());
            preparedStatement.setInt(3, product.getQuantity());
            preparedStatement.setString(4, product.getColor());
            preparedStatement.setInt(5, product.getCategory().getId());
            preparedStatement.setInt(6, product.getId());
            preparedStatement.executeUpdate();
            preparedStatement.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void deleteProductById(int id) {
        try(Connection connection = new MyConnection().getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(DELETE_PRODUCT_BY_ID);
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
            preparedStatement.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public ArrayList<Product> findAllProducts() {
        ArrayList<Product> products = new ArrayList<>();
        try(Connection connection = new MyConnection().getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_PRODUCTS);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                double price = resultSet.getDouble("price");
                int quantity = resultSet.getInt("quantity");
                String color = resultSet.getString("color");
                int categoryId = resultSet.getInt("category_id");
                Category category = findCategoryById(categoryId);
                products.add(new Product(id, name, price, quantity, color, category));
            }
            preparedStatement.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return products;
    }

    public ArrayList<Category> findAllCategories() {
        ArrayList<Category> categories = new ArrayList<>();
        try(Connection connection = new MyConnection().getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_CATEGORY);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                categories.add(new Category(id, name));
            }
            preparedStatement.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return categories;
    }

    public Category findCategoryById(int id) {
        try(Connection connection = new MyConnection().getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_CATEGORY_BY_ID);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                String name = resultSet.getString("name");
                return new Category(id, name);
            }
            preparedStatement.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    public Product findProductById(int id) {
        try(Connection connection = new MyConnection().getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_PRODUCT_BY_ID);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                String name = resultSet.getString("name");
                double price = resultSet.getDouble("price");
                int quantity = resultSet.getInt("quantity");
                String color = resultSet.getString("color");
                Category category = findCategoryById(resultSet.getInt("category_id"));
                return new Product(id, name, price, quantity, color, category);
            }
            preparedStatement.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    public ArrayList<Product> searchProductsByName(String nameSearch) {
        ArrayList<Product> products = new ArrayList<>();
        try(Connection connection = new MyConnection().getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(SEARCH_BY_NAME);
            preparedStatement.setString(1, "%" + nameSearch + "%");
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                double price = resultSet.getDouble("price");
                int quantity = resultSet.getInt("quantity");
                String color = resultSet.getString("color");
                int categoryId = resultSet.getInt("category_id");
                Category category = findCategoryById(categoryId);
                products.add(new Product(id, name, price, quantity, color, category));
            }
            preparedStatement.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return products;
    }
}
