package org.rvl.avro;

import java.io.File;
import java.io.IOException;

import org.apache.avro.file.DataFileReader;
import org.apache.avro.file.DataFileWriter;
import org.apache.avro.io.DatumReader;
import org.apache.avro.io.DatumWriter;
import org.apache.avro.specific.SpecificDatumReader;
import org.apache.avro.specific.SpecificDatumWriter;

import com.example.Customer;

public class SpecificRecordExamples {

  public static void main(String[] args) {

    // step 1 create specific record
    Customer.Builder customerBuilder = Customer.newBuilder();
    customerBuilder.setAge(25);
    customerBuilder.setFirstName("John");
    customerBuilder.setLastName("Doe");
    customerBuilder.setHeight(175.5f);
    customerBuilder.setWeight(80.5f);
    Customer customer = customerBuilder.build();

    System.out.println(customer);

    // write it out to a file
    final DatumWriter<Customer> datumWriter = new SpecificDatumWriter<>(Customer.class);

    try (DataFileWriter<Customer> dataFileWriter = new DataFileWriter<>(datumWriter)) {
      dataFileWriter.create(customer.getSchema(), new File("customer-specific.avro"));
      dataFileWriter.append(customer);
      System.out.println("successfully wrote customer-specific.avro");
    } catch (IOException e) {
      e.printStackTrace();
    }

    // read it from a file
    final File file = new File("customer-specific.avro");
    final DatumReader<Customer> datumReader = new SpecificDatumReader<>(Customer.class);
    final DataFileReader<Customer> dataFileReader;
    try {
      System.out.println("Reading our specific record");
      dataFileReader = new DataFileReader<>(file, datumReader);
      while (dataFileReader.hasNext()) {
        Customer readCustomer = dataFileReader.next();
        System.out.println(readCustomer.toString());
        System.out.println("First name: " + readCustomer.getFirstName());
      }
    } catch (IOException e) {
      e.printStackTrace();
    }

  }
}
