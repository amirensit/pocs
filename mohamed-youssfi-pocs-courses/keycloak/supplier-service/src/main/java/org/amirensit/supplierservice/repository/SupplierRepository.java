
package org.amirensit.supplierservice.repository;

import org.amirensit.supplierservice.domain.Supplier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

/**
 * @author achoubani on 04/03/2020.
 */
@RepositoryRestResource
public interface SupplierRepository extends JpaRepository<Supplier, Long> {
}
