import du.miu.cs.cs489appsd.lab1a.productmgmtapp.model.Product;

import java.util.*;

public class ProductMgmtApp {
    public static void main(String[] args) {

        List<Product> products = Arrays.asList(
                new Product("3128874119", "Banana", new Date(), 124, 0.55),
                new Product("2927458265", "Apple", new Date(), 18, 1.09),
                new Product("9189927460", "Carrot", new Date(), 89, 2.99)
        );


        printProducts(products);
    }

    public static void printProducts(List<Product> products) {
        products.sort(Comparator.comparing(Product::getName));

        printProductsInJSON(products);
        printProductsInXML(products);
        printProductsInCSV(products);
    }


    private static void printProductsInJSON(List<Product> products) {
        // Print products in JSON format
        System.out.println("Products in JSON format:");
        System.out.println("[");

        for (int i = 0; i < products.size(); i++) {
            Product product = products.get(i);
            String json = String.format("\t { \"productId\": %s, \"name\": \"%s\", \"dateSupplied\": \"%s\", \"quantityInStock\": %d, \"unitPrice\": %.2f}",
                    product.getProductId(), product.getName(), product.getDateSupplied(), product.getQuantityInStock(), product.getUnitPrice());
            System.out.println(json + (i < products.size() - 1 ? "," : ""));
        }

        System.out.println("]");
    }

    private static void printProductsInXML(List<Product> products) {
        // Print products in XML format
        System.out.println("Products in XML format:");
        System.out.println("<products>");

        for (Product product : products) {
            System.out.println("<product>");
            System.out.println("    <productId>" + product.getProductId() + "</productId>");
            System.out.println("    <name>" + product.getName() + "</name>");
            System.out.println("    <dateSupplied>" + product.getDateSupplied() + "</dateSupplied>");
            System.out.println("    <quantityInStock>" + product.getQuantityInStock() + "</quantityInStock>");
            System.out.println("    <unitPrice>" + product.getUnitPrice() + "</unitPrice>");
            System.out.println("</product>");
        }

        System.out.println("</products>");
    }

    private static void printProductsInCSV(List<Product> products) {
        // Print products in CSV format
        System.out.println("Products in CSV format:");
        System.out.println("productId,name,dateSupplied,quantityInStock,unitPrice");

        for (Product product : products) {
            System.out.println(product.getProductId() + "," + product.getName() + "," + product.getDateSupplied() + "," +
                    product.getQuantityInStock() + "," + product.getUnitPrice());
        }
    }
}



