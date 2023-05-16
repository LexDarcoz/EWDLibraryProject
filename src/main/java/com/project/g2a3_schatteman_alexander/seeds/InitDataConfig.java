package com.project.g2a3_schatteman_alexander.seeds;

//@Component
//public class InitDataConfig implements CommandLineRunner {
//
//    @Autowired
//    private LocationRepository locationRepo;
//
//    @Autowired
//    private AuthorRepository authorRepo;
//
//    @Autowired
//    private BookRepository repo;
//
//    @Autowired
//    private UserService userService;
//
//    @Override
//    public void run(String... args) throws Exception {
//        locationRepo.save(new Location(5, 300, "Boekentoren"));
//        locationRepo.save(new Location(10, 290, "Boekentoren"));
//        locationRepo.save(new Location(20, 280, "Boekentoren"));
//        locationRepo.save(new Location(30, 270, "Boekentoren"));
//
//        authorRepo.save(new Author("Jesus", "Librarian"));
//        authorRepo.save(new Author("Academie", "De Toren"));
//        authorRepo.save(new Author("Jeff", "De Boeken"));
//        authorRepo.save(new Author("Joske", "De Boeken"));
//
//
//        List<Author> authors = new ArrayList<>();
//        authors.add(new Author("Vittel", "Deloitte"));
//        List<Location> locations = new ArrayList<>();
//        locations.add(new Location(40, 260, "Boekentoren"));
//        Book book = new Book("Spring Boot for Dummies", authors, "123456789", 24.99, 5, locations, new ArrayList<>());
//        repo.save(book);
//
//        for (int i = 1; i <= 100; i++) {
//            repo.save(new Book("Spring Boot for Dummies Version" + i, null, "123456789" + i, 24.99, 5, null, null));
//        }
//
//        List<Book> books = new ArrayList<>();
//        books.add(new Book("Yes Book", null, "45454545", 24.99, 5, null, null));
//        User user = new User("Alexander", "Schatteman", "PWD", "Alexander@gmail.com", "USER,ADMIN", books, 20);
//        userService.addUser(user);
//    }
//}