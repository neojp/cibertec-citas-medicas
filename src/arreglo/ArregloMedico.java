package arreglo;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Comparator;

import clases.Medico;

public class ArregloMedico {

    private ArrayList<Medico> medicos;
    private String file = "src/main/resources/data/medicos.txt";

    // =========================
    // CONSTRUCTOR
    // =========================
    public ArregloMedico() {
        medicos = new ArrayList<>();
        cargar();
    }

    // =========================
    // MÉTODOS BÁSICOS
    // =========================

    public void adicionar(Medico m) {
        medicos.add(m);
        grabar();
    }

    public void eliminar(Medico m) {
        medicos.remove(m);
        grabar();
    }

    public int tamano() {
        return medicos.size();
    }

    public Medico obtener(int i) {
        return medicos.get(i);
    }

    public void actualizar() {
        grabar();
    }

    // =========================
    // BÚSQUEDAS
    // =========================

    public Medico buscarCodMedico(int codMedico) {
        for (Medico m : medicos) {
            if (m.getCodMedico() == codMedico)
                return m;
        }
        return null;
    }

    public Medico buscarPorCmp(String cmp) {
        for (Medico m : medicos) {
            if (m.getCmp().equalsIgnoreCase(cmp))
                return m;
        }
        return null;
    }

    public ArrayList<Medico> buscarEstado(int estado) {
        ArrayList<Medico> lista = new ArrayList<>();
        for (Medico m : medicos) {
            if (m.getEstado() == estado)
                lista.add(m);
        }
        return lista;
    }

    public ArrayList<Medico> buscarNombres(String nombres) {
        ArrayList<Medico> lista = new ArrayList<>();
        for (Medico m : medicos) {
            if (m.getNombres().equalsIgnoreCase(nombres))
                lista.add(m);
        }
        return lista;
    }

    public ArrayList<Medico> buscarApellidos(String apellidos) {
        ArrayList<Medico> lista = new ArrayList<>();
        for (Medico m : medicos) {
            if (m.getApellidos().equalsIgnoreCase(apellidos))
                lista.add(m);
        }
        return lista;
    }

    public ArrayList<Medico> buscarEspecialidad(String especialidad) {
        ArrayList<Medico> lista = new ArrayList<>();
        for (Medico m : medicos) {
            if (m.getEspecialidad().equalsIgnoreCase(especialidad))
                lista.add(m);
        }
        return lista;
    }

    // =========================
    // GENERAR PRÓXIMO CÓDIGO
    // =========================

    public int proximoCodigo() {
        if (medicos.isEmpty())
            return 501;

        int max = medicos.stream()
                .mapToInt(Medico::getCodMedico)
                .max()
                .orElse(500);

        return max + 1;
    }

    // =========================
    // ORDENAMIENTOS
    // =========================

    public void ordenarPorCodigo() {
        medicos.sort(Comparator.comparingInt(Medico::getCodMedico));
    }

    public void ordenarPorNombreCompleto() {
        medicos.sort(Comparator.comparing(
                Medico::getNombreCompleto,
                String.CASE_INSENSITIVE_ORDER));
    }

    // =========================
    // ARCHIVOS
    // =========================

    private void cargar() {
        try {
            File archivo = new File(file);
            if (!archivo.exists())
                return;

            BufferedReader br = new BufferedReader(new FileReader(file));
            String linea;

            while ((linea = br.readLine()) != null) {
                String[] d = linea.split(";", -1);

                int cod = Integer.parseInt(d[0].trim());
                String nom = d[1].trim();
                String ape = d[2].trim();
                String esp = d[3].trim();
                String cmp = d[4].trim();
                int est = Integer.parseInt(d[5].trim());

                medicos.add(new Medico(cod, nom, ape, esp, cmp, est));
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

            for (Medico m : medicos) {
                pw.println(
                        m.getCodMedico() + ";" +
                        m.getNombres() + ";" +
                        m.getApellidos() + ";" +
                        m.getEspecialidad() + ";" +
                        m.getCmp() + ";" +
                        m.getEstado()
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