package arreglo;

import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Comparator;

import clases.Cita;

public class ArregloCita {

    private ArrayList<Cita> arr;
    private String file = "src/main/resources/data/citas.txt";

    // Constructor
    public ArregloCita() {
        arr = new ArrayList<>();
        cargar();
    }

    // =========================
    // MÉTODOS BÁSICOS
    // =========================

    public void adicionar(Cita x) {
        arr.add(x);
        grabar();
    }

    public void eliminar(Cita x) {
        arr.remove(x);
        grabar();
    }

    public void eliminarPorCodigo(int numCita) {
        arr.removeIf(c -> c.getNumCita() == numCita);
        grabar();
    }

    public void actualizarArchivo() {
        grabar();
    }

    public int tamano() {
        return arr.size();
    }

    public Cita obtener(int i) {
        return arr.get(i);
    }

    public Cita buscarNumCita(int numCita) {
        for (Cita c : arr)
            if (c.getNumCita() == numCita)
                return c;
        return null;
    }

    // =========================
    // 🔹 MÉTODO AGREGADO PARA COMPATIBILIDAD
    // =========================

    // Este método evita el error:
    // "The method buscarCodConsultorio(int) is undefined"

    public ArrayList<Cita> buscarCodConsultorio(int codConsultorio) {
        return buscarPorConsultorio(codConsultorio);
    }

    // =========================
    // GENERAR CÓDIGO
    // =========================

    public int proximoCodigo() {
        if (arr.isEmpty()) return 1001;

        int max = arr.stream()
                     .mapToInt(Cita::getNumCita)
                     .max()
                     .orElse(1000);

        return max + 1;
    }

    // =========================
    // BÚSQUEDAS (DEVUELVEN LISTAS)
    // =========================

    public ArrayList<Cita> buscarPorPaciente(int codPaciente) {
        ArrayList<Cita> lista = new ArrayList<>();
        for (Cita c : arr)
            if (c.getCodPaciente() == codPaciente)
                lista.add(c);
        return lista;
    }

    public ArrayList<Cita> buscarPorMedico(int codMedico) {
        ArrayList<Cita> lista = new ArrayList<>();
        for (Cita c : arr)
            if (c.getCodMedico() == codMedico)
                lista.add(c);
        return lista;
    }

    public ArrayList<Cita> buscarPorConsultorio(int codConsultorio) {
        ArrayList<Cita> lista = new ArrayList<>();
        for (Cita c : arr)
            if (c.getCodConsultorio() == codConsultorio)
                lista.add(c);
        return lista;
    }

    public ArrayList<Cita> buscarPorEstado(int estado) {
        ArrayList<Cita> lista = new ArrayList<>();
        for (Cita c : arr)
            if (c.getEstado() == estado)
                lista.add(c);
        return lista;
    }

    public ArrayList<Cita> buscarPorFecha(String fecha) {
        ArrayList<Cita> lista = new ArrayList<>();
        for (Cita c : arr) {
			System.out.println("Comparando: " + c.getFecha() + " con " + fecha);
            if (c.getFecha().equalsIgnoreCase(fecha)){
				lista.add(c);
			}
		}
		
        return lista;
    }

    public ArrayList<Cita> buscarPorHora(String hora) {
        ArrayList<Cita> lista = new ArrayList<>();
        for (Cita c : arr)
            if (c.getHora().equalsIgnoreCase(hora))
                lista.add(c);
        return lista;
    }

    public ArrayList<Cita> buscarPorMotivo(String motivo) {
        ArrayList<Cita> lista = new ArrayList<>();
        for (Cita c : arr)
            if (c.getMotivo().equalsIgnoreCase(motivo))
                lista.add(c);
        return lista;
    }

    // =========================
    // CITAS FUTURAS
    // =========================

    public ArrayList<Cita> buscarFuturasPorPaciente(int codPaciente) {
        return filtrarFuturas(c -> c.getCodPaciente() == codPaciente);
    }

    public ArrayList<Cita> buscarFuturasPorMedico(int codMedico) {
        return filtrarFuturas(c -> c.getCodMedico() == codMedico);
    }

    public ArrayList<Cita> buscarFuturasPorConsultorio(int codConsultorio) {
        return filtrarFuturas(c -> c.getCodConsultorio() == codConsultorio);
    }

    private ArrayList<Cita> filtrarFuturas(java.util.function.Predicate<Cita> condicion) {
        ArrayList<Cita> lista = new ArrayList<>();
        DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
        LocalDateTime ahora = LocalDateTime.now();

        for (Cita c : arr) {
            if (condicion.test(c) && c.getEstado() == 0) {

                LocalDateTime fechaCita =
                        LocalDateTime.parse(c.getFecha() + " " + c.getHora(), formato);

                if (fechaCita.isAfter(ahora))
                    lista.add(c);
            }
        }
        return lista;
    }

    public int contarFuturasPorPaciente(int codPaciente) {
        return buscarFuturasPorPaciente(codPaciente).size();
    }

    // =========================
    // ORDENAMIENTOS
    // =========================

    public void ordenarPorFecha() {
        arr.sort(Comparator.comparing(Cita::getLocalDateTime));
    }

    public void ordenarPorNumero() {
        arr.sort(Comparator.comparingInt(Cita::getNumCita));
    }

    // =========================
    // ARCHIVOS
    // =========================

    private void cargar() {
        try {
            File archivo = new File(file);
            if (!archivo.exists()) return;

            BufferedReader br = new BufferedReader(new FileReader(file));
            String linea;

            while ((linea = br.readLine()) != null) {
                String[] s = linea.split(";", -1);

                arr.add(new Cita(
                        Integer.parseInt(s[0].trim()),
                        Integer.parseInt(s[1].trim()),
                        Integer.parseInt(s[2].trim()),
                        Integer.parseInt(s[3].trim()),
                        Integer.parseInt(s[4].trim()),
                        s[5].trim(),
                        s[6].trim(),
                        s[7].trim()
                ));
            }
            br.close();
        } catch (Exception e) {
            System.out.println("Error al cargar: " + e.getMessage());
        }
    }

    public void grabar() {
        try {
            crearArchivoSiNoExiste();

            PrintWriter pw = new PrintWriter(new FileWriter(file));

            for (Cita c : arr) {
                pw.println(
                        c.getNumCita() + ";" +
                        c.getCodPaciente() + ";" +
                        c.getCodMedico() + ";" +
                        c.getCodConsultorio() + ";" +
                        c.getEstado() + ";" +
                        c.getFecha() + ";" +
                        c.getHora() + ";" +
                        c.getMotivo()
                );
            }

            pw.close();
        } catch (Exception e) {
            System.out.println("Error al grabar: " + e.getMessage());
        }
    }

    private void crearArchivoSiNoExiste() {
        try {
            File archivo = new File(file);
            if (!archivo.exists())
                archivo.createNewFile();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}