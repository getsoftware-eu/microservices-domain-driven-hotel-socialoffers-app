//package eu.getsoftware.hotelico.hotelapp.adapter.in.web.controller.hotel;
//
//@Slf4j
//@Validated
//@RestController
//@RequestMapping(value = HotelController.REQUEST_MAPPING, produces = {HAL_JSON_VALUE, APPLICATION_JSON_VALUE})
//@RequiredArgsConstructor
//public class Hotel2Controller {
// 
//
//    // ...
//
//    @GetMapping
//    public ResponseEntity<PagedResources<HotelListResource>> listHotels(@ModelAttribute HotelListResourceAssembler resourceAssembler,
//                                                                        @OnlyRecognizedSort Pageable pageable
//    ) {
//        Page<Hotel> products = productRepository.findAll(pageable);
//        return ResponseEntity.ok(toPagedResources(resourceAssembler, products));
//    }
//
//    @GetMapping("/{id}")
//    public ResponseEntity<HotelDetailResource> findById(@ModelAttribute HotelDetailResourceAssembler resourceAssembler,
//                                                        @PathVariable UUID id
//    ) {
//        Optional<Hotel> product = productRepository.findVisibleById(id.toString());
//
//        return withMdc(product).executeAndReturn(() -> product
//                .map(resourceAssembler::toResource)
//                .map(ResponseEntity::ok)
//                .orElse(new ResponseEntity<>(NOT_FOUND))
//        );
//    }
//
//    // ...
//
//    // ...
//
//    @GetMapping("/search/find-by-tags")
//    public ResponseEntity<PagedResources<ProductListResource>> findByTags(@ModelAttribute ProductListResourceAssembler resourceAssembler,
//                                                                          @RequestParam(name = "tag", required = false) Set<String> tags,
//                                                                          @OnlyRecognizedSort @PageableDefault(sort = DEFAULT_SORT, direction = DESC, size = DEFAULT_PAGE_SIZE) Pageable pageable
//    ) {
//        Page<Product> products = productRepository.findVisibleByTags(firstNonNull(tags, NO_TAGS), pageable);
//
//        return ResponseEntity.ok(toPagedResources(resourceAssembler, products));
//    }
//
//    @GetMapping("/search/find-by-search-term")
//    public ResponseEntity<PagedResources<ProductListResource>> findBySearchTerm(@ModelAttribute ProductListResourceAssembler resourceAssembler,
//                                                                                @RequestParam(name = "query", required = false) String query,
//                                                                                @OnlyRecognizedSort Pageable pageable
//    ) {
//        Page<Product> products = productRepository.findVisibleBySearchTerm(query, pageable);
//
//        return ResponseEntity.ok(toPagedResources(resourceAssembler, products));
//    }
//
//    // ...
//
//    @GetMapping("/search/find-by-category")
//    public ResponseEntity<PagedResources<ProductListResource>> findByCategory(@ModelAttribute ProductListResourceAssembler resourceAssembler,
//                                                                              @RequestParam(value = "categoryId") Optional<Category> category,
//                                                                              @OnlyRecognizedSort Pageable pageable
//    ) {
//        return withMdc(category).executeAndReturn(() -> category
//                .map(Category::getQuery)
//                .map(query -> productRepository.findVisibleByCategoryQuery(query, pageable))
//                .map(products -> toPagedResources(resourceAssembler, products))
//                .map(ResponseEntity::ok)
//                .orElse(new ResponseEntity<>(NOT_FOUND))
//        );
//    }
//
//    @GetMapping("/search/find-by-query")
//    public ResponseEntity<PagedResources<ProductListResource>> findByQueryWithRequestParam(@ModelAttribute ProductListResourceAssembler resourceAssembler,
//                                                                                           @RequestParam(value = CATEGORY_QUERY_REQUEST_PARAM_NAME) String query,
//                                                                                           @OnlyRecognizedSort Pageable pageable
//    ) {
//        CategoryQuery categoryQuery = CategoryQuery.builder(objectMapper).build(query);
//        Page<Product> products = productRepository.findVisibleByCategoryQuery(categoryQuery, pageable);
//        return ResponseEntity.ok(toPagedResources(resourceAssembler, products));
//    }
//
//    private PagedResources<ProductListResource> toPagedResources(ProductListResourceAssembler resourceAssembler, Page<Product> products) {
//        return products.getContent().isEmpty() ?
//                (PagedResources<ProductListResource>) pagedResourcesAssembler.toEmptyResource(products, ProductListResource.class, null) :
//                pagedResourcesAssembler.toResource(products, resourceAssembler);
//    }
//
//    // ...
//}
///**
//Deployment
//Deployment: CI/CD-Pipeline
//
//DDD & CQRS Lab in the Oracle Cloud
//
//
