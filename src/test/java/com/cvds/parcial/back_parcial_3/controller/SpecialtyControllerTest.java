@SpringBootTest
@AutoConfigureMockMvc
class SpecialtyControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private SpecialtyService specialtyService;

    @Test
    void testGetAllSpecialties() throws Exception {
        List<Specialty> list = List.of(
                new Specialty("medicina general", "desc", "Dr. X", "Piso 1", "img1"),
                new Specialty("psicología", "desc2", "Dra. Y", "Piso 2", "img2")
        );

        Mockito.when(specialtyService.getAllSpecialties()).thenReturn(list);

        mockMvc.perform(get("/especialidades"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(2))
                .andExpect(jsonPath("$[0].nombre").value("medicina general"));
    }
}
