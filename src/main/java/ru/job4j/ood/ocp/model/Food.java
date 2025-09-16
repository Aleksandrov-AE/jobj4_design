package ru.job4j.ood.ocp.model;

import java.time.LocalDate;
import java.util.Objects;

public class Food {
   private final String name;
   private final LocalDate expiryDate;
   private final LocalDate createDate;
   private final double price;
   double discount;


   public Food(String name, LocalDate expiryDate, LocalDate createDate, double price) {
      if (expiryDate.isBefore(createDate) || expiryDate.isEqual(createDate)) {
         throw new IllegalArgumentException("expiryDate must be after createDate");
      }

      this.name = name;
      this.expiryDate = expiryDate;
      this.createDate = createDate;
      this.price = price;
   }



   public String getName() {
      return name;
   }

   public LocalDate getExpiryDate() {
      return expiryDate;
   }

   public LocalDate getCreateDate() {
      return createDate;
   }


   public double getDiscount() {
      return discount;
   }

   public double currentPrice() {
      return price - (price * discount);
   }

   public void setDiscount(double discount) {
      if (discount < 0 || discount > 1) {
         throw new IllegalArgumentException("discount must be between 0 and 1");
      }
      this.discount = discount;
   }

   @Override
   public boolean equals(Object o) {
      if (this == o) {
         return true;
      }
      if (o == null || getClass() != o.getClass()) {
         return false;
      }
      Food food = (Food) o;
      return Objects.equals(name, food.name);
   }

   @Override
   public int hashCode() {
      return Objects.hash(name);
   }
}
