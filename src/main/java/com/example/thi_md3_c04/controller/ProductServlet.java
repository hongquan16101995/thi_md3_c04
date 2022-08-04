package com.example.thi_md3_c04.controller;

import com.example.thi_md3_c04.model.Category;
import com.example.thi_md3_c04.model.Product;
import com.example.thi_md3_c04.service.ProductService;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "ProductServlet", value = "/product")
public class ProductServlet extends HttpServlet {
    private final ProductService productService = new ProductService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action == null) {
            action = "";
        }
        switch (action) {
            case "create":
                createProductGet(request, response);
                break;
            case "update":
                updateProductGet(request, response);
                break;
            case "delete":
                deleteProduct(request, response);
                break;
            default:
                displayAllProduct(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action == null) {
            action = "";
        }
        switch (action) {
            case "create":
                createProductPost(request, response);
                break;
            case "update":
                updateProductPost(request, response);
                break;
            case "search":
                searchProductByName(request, response);
        }
    }

    private void displayAllProduct(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("display.jsp");
        request.setAttribute("products", productService.findAllProducts());
        requestDispatcher.forward(request, response);
    }

    private void updateProductGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("update.jsp");
        request.setAttribute("p", productService.findProductById(id));
        request.setAttribute("categories", productService.findAllCategories());
        requestDispatcher.forward(request, response);
    }

    private void updateProductPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        Product product = productService.findProductById(id);
        product.setName(request.getParameter("name"));
        product.setPrice(Double.parseDouble(request.getParameter("price")));
        product.setQuantity(Integer.parseInt(request.getParameter("quantity")));
        product.setColor(request.getParameter("color"));
        product.setName(request.getParameter("name"));
        product.setCategory(productService.findCategoryById(
                Integer.parseInt(request.getParameter("category"))));
        productService.updateProduct(product);
        response.sendRedirect("/product");
    }

    private void createProductGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("create.jsp");
        request.setAttribute("categories", productService.findAllCategories());
        requestDispatcher.forward(request, response);
    }

    private void createProductPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String name = request.getParameter("name");
        double price = Double.parseDouble(request.getParameter("price"));
        int quantity = Integer.parseInt(request.getParameter("quantity"));
        String color = request.getParameter("color");
        int categoryId = Integer.parseInt(request.getParameter("category"));
        Category category = productService.findCategoryById(categoryId);
        Product product = new Product(name, price, quantity, color, category);
        productService.createProduct(product);
        response.sendRedirect("/product");
    }

    private void deleteProduct(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        productService.deleteProduct(id);
        response.sendRedirect("/product");
    }

    private void searchProductByName(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String name = request.getParameter("search");
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("display.jsp");
        request.setAttribute("products", productService.searchProductsByName(name));
        requestDispatcher.forward(request, response);
    }
}
