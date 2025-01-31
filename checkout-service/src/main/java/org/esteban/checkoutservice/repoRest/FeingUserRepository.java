package org.esteban.checkoutservice.repoRest;

import org.esteban.checkoutservice.modelDTO.ClientDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "user-service")
public interface FeingUserRepository {
    @RequestMapping(method= RequestMethod.GET, value="/users")
    ClientDTO getUserByEmail(@PathVariable("id") Long id);

}
