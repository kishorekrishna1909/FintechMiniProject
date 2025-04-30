    package com.example.CustomerProject.Model;

    import jakarta.persistence.*;
    import lombok.AllArgsConstructor;
    import lombok.Data;
    import lombok.NoArgsConstructor;

    import java.util.List;

    // @Data
    // @NoArgsConstructor
    // @AllArgsConstructor
    // public class Customer_Contact {

    //     @Id
    //     @GeneratedValue(strategy = GenerationType.IDENTITY)
    //     private Long id;

    //     @ElementCollection
    //     private List<ContactDetail> contactDetails;  // Unified list of contact info
    // }

    
    @Entity
    @Table(name = "customer_contact_details")
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public class Customer_Contact_Detail {
       
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;
        
        @Enumerated(EnumType.STRING)
        private Types.contactType contactType;  // PHONE or EMAIL

        @Enumerated(EnumType.STRING)
        private Types.Type type;  // PERSONAL or OFFICIAL

        private String value;  // phone number or email value
    }
