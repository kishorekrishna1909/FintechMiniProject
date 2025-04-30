    package com.example.SharedModule.Model;

    import jakarta.persistence.*;
    import lombok.AllArgsConstructor;
    import lombok.Data;
    import lombok.NoArgsConstructor;

    // import java.util.List;

   import com.example.SharedModule.Model.Types.contactType;
   import com.example.SharedModule.Model.Types.Type;
   import lombok.ToString;
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    // public class Customer_Contact {

    //     @Id
    //     @GeneratedValue(strategy = GenerationType.IDENTITY)
    //     private Long id;

    //     @ElementCollection
    //     private List<ContactDetail> contactDetails;  // Unified list of contact info
    // }

    //  @Entity
    // @Table(name = "customer_contact_details")
    // @Data
    // @NoArgsConstructor
    // @AllArgsConstructor
    public class Customer_Contact_Detail {
       
        private Long id;
        
        @Enumerated(EnumType.STRING)
        private contactType contactType;  // PHONE or EMAIL

        @Enumerated(EnumType.STRING)
        private Type type;  // PERSONAL or OFFICIAL

        private String value;  // phone number or email value
    }
