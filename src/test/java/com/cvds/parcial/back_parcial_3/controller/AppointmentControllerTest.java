@SpringBootTest
@AutoConfigureMockMvc
class AppointmentControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AppointmentService appointmentService;

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Test
    void testCreateAppointment() throws Exception {
        AppointmentDTO dto = new AppointmentDTO("Ana", "1234", "ana@mail.com", "25-05-2025", "medicina general");
        Appointment saved = new Appointment("1", "Ana", "1234", "ana@mail.com", "25-05-2025", "medicina general", "Dr. X", "Piso 1", "Confirmada");

        Mockito.when(appointmentService.createAppointment(any(AppointmentDTO.class))).thenReturn(saved);

        mockMvc.perform(post("/citas")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(dto)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.correo").value("ana@mail.com"))
                .andExpect(jsonPath("$.estado").value("Confirmada"));
    }

    @Test
    void testGetAppointmentsByCorreo() throws Exception {
        List<Appointment> citas = List.of(new Appointment("1", "Ana", "1234", "ana@mail.com", "25-05-2025", "psicología", "Dra. Laura", "Piso 2", "Confirmada"));

        Mockito.when(appointmentService.getAppointmentsByCorreo("ana@mail.com")).thenReturn(citas);

        mockMvc.perform(get("/citas/ana@mail.com"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].especialidad").value("psicología"));
    }

    @Test
    void testCancelAppointment() throws Exception {
        Appointment cancelada = new Appointment("1", "Ana", "1234", "ana@mail.com", "25-05-2025", "psicología", "Dra. Laura", "Piso 2", "Cancelada");

        Mockito.when(appointmentService.cancelAppointment("1")).thenReturn(cancelada);

        mockMvc.perform(put("/citas/1/cancelar"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.estado").value("Cancelada"));
    }

    @Test
    void testGetAppointmentsByCorreoAndEstado() throws Exception {
        List<Appointment> citas = List.of(new Appointment("2", "Ana", "1234", "ana@mail.com", "25-05-2025", "psicología", "Dra. Laura", "Piso 2", "Cancelada"));

        Mockito.when(appointmentService.getAppointmentsByCorreoAndEstado("ana@mail.com", "Cancelada")).thenReturn(citas);

        mockMvc.perform(get("/citas/ana@mail.com/estado/Cancelada"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].estado").value("Cancelada"));
    }
}
