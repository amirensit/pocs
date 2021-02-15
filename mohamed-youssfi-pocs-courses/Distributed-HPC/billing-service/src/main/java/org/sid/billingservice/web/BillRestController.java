package org.sid.billingservice.web;

import org.sid.billingservice.entities.Bill;
import org.sid.billingservice.feign.CustomerRestClient;
import org.sid.billingservice.feign.ProductRestClient;
import org.sid.billingservice.models.Customer;
import org.sid.billingservice.models.Product;
import org.sid.billingservice.repositories.BillRepository;
import org.sid.billingservice.repositories.ProductItemRepository;
import org.springframework.hateoas.PagedModel;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BillRestController {

    private final BillRepository billRepository;
    private final ProductItemRepository productItemRepository;
    private final CustomerRestClient customerRestClient;
    private final ProductRestClient productRestClient;

    public BillRestController(BillRepository billRepository,
                              ProductItemRepository productItemRepository,
                              CustomerRestClient customerRestClient,
                              ProductRestClient productRestClient) {
        this.billRepository = billRepository;
        this.productItemRepository = productItemRepository;
        this.customerRestClient = customerRestClient;
        this.productRestClient = productRestClient;
    }

    @GetMapping("/fullBill/{id}")
    public Bill getBill(@PathVariable("id") Long id) {
        Bill bill = billRepository.findById(id).get();
        Customer customer = customerRestClient.getCustomerById(bill.getCustomerId());
        bill.setCustomer(customer);
        bill.getProductItems().forEach(
                productItem -> {
                    Product product = productRestClient.findProductById(productItem.getProductId());
                    productItem.setProduct(product);
                }
        );
        return bill;
    }
}
