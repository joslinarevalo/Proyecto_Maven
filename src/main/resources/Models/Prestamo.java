package Models;

import java.util.Date;

public class Prestamo {
        private String codigoPrestamo;

        private Alumno carnet;

        private Libro codigoLibro;

        private Date fechaPrestamo;

        private Integer cantidadPrestamo;

        public Prestamo() {
        }

        public String getCodigoPrestamo() {
            return codigoPrestamo;
        }

        public void setCodigoPrestamo(String codigoPrestamo) {
            this.codigoPrestamo = codigoPrestamo;
        }

        public Alumno getCarnet() {
            return carnet;
        }

        public void setCarnet(Alumno carnet) {
            this.carnet = carnet;
        }

        public Libro getCodigoLibro() {
            return codigoLibro;
        }

        public void setCodigoLibro(Libro codigoLibro) {
            this.codigoLibro = codigoLibro;
        }

        public Date getFechaPrestamo() {
            return fechaPrestamo;
        }

        public void setFechaPrestamo(Date fechaPrestamo) {
            this.fechaPrestamo = fechaPrestamo;
        }

        public Integer getCantidadPrestamo() {
            return cantidadPrestamo;
        }

        public void setCantidadPrestamo(Integer cantidadPrestamo) {
            this.cantidadPrestamo = cantidadPrestamo;
        }
    }

