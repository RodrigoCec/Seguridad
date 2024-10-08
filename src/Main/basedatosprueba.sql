-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 22-08-2024 a las 21:24:53
-- Versión del servidor: 10.4.32-MariaDB
-- Versión de PHP: 8.2.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `basedatosprueba`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `alumnos`
--

CREATE TABLE `alumnos` (
  `ID_Alumno` int(11) NOT NULL,
  `Nombre` text NOT NULL,
  `apellidoPaterno` text NOT NULL,
  `apellidoMaterno` text NOT NULL,
  `Grado` text NOT NULL,
  `Grupo` text NOT NULL,
  `matricula` text NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;

--
-- Volcado de datos para la tabla `alumnos`
--

INSERT INTO `alumnos` (`ID_Alumno`, `Nombre`, `apellidoPaterno`, `apellidoMaterno`, `Grado`, `Grupo`, `matricula`) VALUES
(1, 'Rodrigo', 'Simon', 'Corona', '4', 'J', 'F4E1BS7C6R1'),
(4, 'Jesús Aldair', 'Morales', 'Romero', '4', 'J', 'F4E1BM4R5J7'),
(5, 'Evelyn', 'Ramirez', 'Aguila', '4', 'A', 'F4E1BR3A4E6'),
(6, 'Juan', 'Pedro', 'Salazar', '1', 'H', 'F1E8AP3S1J5'),
(7, 'Godinez', 'Maseroy', 'Mezquite', '4', 'J', 'F4E1BM4M3G5'),
(8, 'Maria', 'Pedraza', 'Sanches', '1', 'B', 'F1E2AP8S8M1'),
(9, 'Jose Maria', 'Pelcastre', 'Guerrrero', '4', 'J', 'F4E1BP8G8J8'),
(10, 'Juan', 'Marquez', 'Ponciano', '4', 'J', 'F4E1BM2P1J3'),
(11, 'Saul', 'Perez', 'Ortiz', '4', 'J', 'F4E1BP7O7S2'),
(12, 'null', 'null', 'null', 'null', 'null', 'null'),
(13, 'Jesus', 'Jesus', 'Jesus', '4', 'J', '1231245534'),
(14, 'Julian ', 'Junites', 'Juntino', '1', 'A', '231412341234');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `cuartosemestre`
--

CREATE TABLE `cuartosemestre` (
  `id_Codigo` int(11) NOT NULL,
  `Grupo` text NOT NULL,
  `Lunes` text NOT NULL,
  `Martes` text NOT NULL,
  `Miercoles` text NOT NULL,
  `Jueves` text NOT NULL,
  `Viernes` text NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;

--
-- Volcado de datos para la tabla `cuartosemestre`
--

INSERT INTO `cuartosemestre` (`id_Codigo`, `Grupo`, `Lunes`, `Martes`, `Miercoles`, `Jueves`, `Viernes`) VALUES
(1, 'A', '315000630', '513001230', '313002130', '413002130', '113001330'),
(2, 'B', '123456789', '123456789', '123456789', '123456789', '123456789'),
(3, 'C', '123456789', '123456789', '123456789', '123456789', '123456789'),
(4, 'D', '123456789', '123456789', '123456789', '123456789', '123456789'),
(5, 'E', '123456789', '123456789', '123456789', '123456789', '123456789'),
(6, 'F', '123456789', '123456789', '123456789', '123456789', '123456789'),
(7, 'G', '123456789', '123456789', '123456789', '123456789', '123456789'),
(8, 'H', '123456789', '123456789', '123456789', '123456789', '123456789'),
(9, 'I', '123456789', '123456789', '123456789', '123456789', '123456789'),
(10, 'j', '123456789', '123456789', '123456789', '123456789', '123456789');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `primersemestre`
--

CREATE TABLE `primersemestre` (
  `id_Codigo` int(11) NOT NULL,
  `Grupo` text NOT NULL,
  `Lunes` text NOT NULL,
  `Martes` text NOT NULL,
  `Miercoles` text NOT NULL,
  `Jueves` text NOT NULL,
  `Viernes` text NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;

--
-- Volcado de datos para la tabla `primersemestre`
--

INSERT INTO `primersemestre` (`id_Codigo`, `Grupo`, `Lunes`, `Martes`, `Miercoles`, `Jueves`, `Viernes`) VALUES
(1, 'A', '313000630', '313001830', '313001830', '313001830', '313001830'),
(2, 'B', '123456789', '123456789', '123456789', '123456789', '123456789'),
(3, 'C', '123456789', '123456789', '123456789', '123456789', '123456789'),
(4, 'D', '123456789', '123456789', '123456789', '123456789', '123456789'),
(5, 'E', '123456789', '123456789', '123456789', '123456789', '123456789'),
(6, 'F', '123456789', '123456789', '123456789', '123456789', '123456789'),
(7, 'G', '123456789', '123456789', '123456789', '123456789', '123456789'),
(8, 'H', '123456789', '123456789', '123456789', '123456789', '123456789'),
(9, 'I', '123456789', '123456789', '123456789', '123456789', '123456789'),
(10, 'j', '107456789', '123456789', '123456789', '123456789', '123456789');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `quintosemestre`
--

CREATE TABLE `quintosemestre` (
  `id_Codigo` int(11) NOT NULL,
  `Grupo` text NOT NULL,
  `Lunes` text NOT NULL,
  `Martes` text NOT NULL,
  `Miercoles` text NOT NULL,
  `Jueves` text NOT NULL,
  `Viernes` text NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;

--
-- Volcado de datos para la tabla `quintosemestre`
--

INSERT INTO `quintosemestre` (`id_Codigo`, `Grupo`, `Lunes`, `Martes`, `Miercoles`, `Jueves`, `Viernes`) VALUES
(1, 'A', '313000612', '513002130', '113002130', '113002130', '113001330'),
(2, 'B', '123456789', '123456789', '123456789', '123456789', '123456789'),
(3, 'C', '123456789', '123456789', '123456789', '123456789', '123456789'),
(4, 'D', '123456789', '123456789', '123456789', '123456789', '123456789'),
(5, 'E', '123456789', '123456789', '123456789', '123456789', '123456789'),
(6, 'F', '123456789', '123456789', '123456789', '123456789', '123456789'),
(7, 'G', '123456789', '123456789', '123456789', '123456789', '123456789'),
(8, 'H', '123456789', '123456789', '123456789', '123456789', '123456789'),
(9, 'I', '123456789', '123456789', '123456789', '123456789', '123456789'),
(10, 'j', '123456789', '123456789', '123456789', '123456789', '123456789');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `segundosemestre`
--

CREATE TABLE `segundosemestre` (
  `id_Codigo` int(11) NOT NULL,
  `Grupo` text NOT NULL,
  `Lunes` text NOT NULL,
  `Martes` text NOT NULL,
  `Miercoles` text NOT NULL,
  `Jueves` text NOT NULL,
  `Viernes` text NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;

--
-- Volcado de datos para la tabla `segundosemestre`
--

INSERT INTO `segundosemestre` (`id_Codigo`, `Grupo`, `Lunes`, `Martes`, `Miercoles`, `Jueves`, `Viernes`) VALUES
(1, 'A', '513000630', '513001830', '513001830', '513001830', '513001830'),
(2, 'B', '123456789', '123456789', '123456789', '123456789', '123456789'),
(3, 'C', '123456789', '123456789', '123456789', '123456789', '123456789'),
(4, 'D', '123456789', '123456789', '123456789', '123456789', '123456789'),
(5, 'E', '123456789', '123456789', '123456789', '123456789', '123456789'),
(6, 'F', '123456789', '123456789', '123456789', '123456789', '123456789'),
(7, 'G', '123456789', '123456789', '123456789', '123456789', '123456789'),
(8, 'H', '123456789', '123456789', '123456789', '123456789', '123456789'),
(9, 'I', '123456789', '123456789', '123456789', '123456789', '123456789'),
(10, 'j', '123456789', '123456789', '123456789', '123456789', '123456789');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `sextosemestre`
--

CREATE TABLE `sextosemestre` (
  `id_Codigo` int(11) NOT NULL,
  `Grupo` text NOT NULL,
  `Lunes` text NOT NULL,
  `Martes` text NOT NULL,
  `Miercoles` text NOT NULL,
  `Jueves` text NOT NULL,
  `Viernes` text NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;

--
-- Volcado de datos para la tabla `sextosemestre`
--

INSERT INTO `sextosemestre` (`id_Codigo`, `Grupo`, `Lunes`, `Martes`, `Miercoles`, `Jueves`, `Viernes`) VALUES
(1, 'A', '115121912', '113002130', '113002130', '113002130', '113001330'),
(2, 'B', '123456789', '123456789', '123456789', '123456789', '123456789'),
(3, 'C', '123456789', '123456789', '123456789', '123456789', '123456789'),
(4, 'D', '123456789', '123456789', '123456789', '123456789', '123456789'),
(5, 'E', '123456789', '123456789', '123456789', '123456789', '123456789'),
(6, 'F', '123456789', '123456789', '123456789', '123456789', '123456789'),
(7, 'G', '123456789', '123456789', '123456789', '123456789', '123456789'),
(8, 'H', '123456789', '123456789', '123456789', '123456789', '123456789'),
(9, 'I', '123456789', '123456789', '123456789', '123456789', '123456789'),
(10, 'j', '123456789', '123456789', '123456789', '123456789', '123456789');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `tercersemestre`
--

CREATE TABLE `tercersemestre` (
  `id_Codigo` int(11) NOT NULL,
  `Grupo` text NOT NULL,
  `Lunes` text NOT NULL,
  `Martes` text NOT NULL,
  `Miercoles` text NOT NULL,
  `Jueves` text NOT NULL,
  `Viernes` text NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;

--
-- Volcado de datos para la tabla `tercersemestre`
--

INSERT INTO `tercersemestre` (`id_Codigo`, `Grupo`, `Lunes`, `Martes`, `Miercoles`, `Jueves`, `Viernes`) VALUES
(1, 'A', '314000630', '313001230', '313002130', '313002130', '413001830'),
(2, 'B', '123456789', '123456789', '123456789', '123456789', '123456789'),
(3, 'C', '123456789', '123456789', '123456789', '123456789', '123456789'),
(4, 'D', '123456789', '123456789', '123456789', '123456789', '123456789'),
(5, 'E', '123456789', '123456789', '123456789', '123456789', '123456789'),
(6, 'F', '123456789', '123456789', '123456789', '123456789', '123456789'),
(7, 'G', '123456789', '123456789', '123456789', '123456789', '123456789'),
(8, 'H', '123456789', '123456789', '123456789', '123456789', '123456789'),
(9, 'I', '123456789', '123456789', '123456789', '123456789', '123456789'),
(10, 'j', '123456789', '123456789', '123456789', '123456789', '123456789');

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `alumnos`
--
ALTER TABLE `alumnos`
  ADD PRIMARY KEY (`ID_Alumno`);

--
-- Indices de la tabla `cuartosemestre`
--
ALTER TABLE `cuartosemestre`
  ADD PRIMARY KEY (`id_Codigo`);

--
-- Indices de la tabla `primersemestre`
--
ALTER TABLE `primersemestre`
  ADD PRIMARY KEY (`id_Codigo`);

--
-- Indices de la tabla `quintosemestre`
--
ALTER TABLE `quintosemestre`
  ADD PRIMARY KEY (`id_Codigo`);

--
-- Indices de la tabla `segundosemestre`
--
ALTER TABLE `segundosemestre`
  ADD PRIMARY KEY (`id_Codigo`);

--
-- Indices de la tabla `sextosemestre`
--
ALTER TABLE `sextosemestre`
  ADD PRIMARY KEY (`id_Codigo`);

--
-- Indices de la tabla `tercersemestre`
--
ALTER TABLE `tercersemestre`
  ADD PRIMARY KEY (`id_Codigo`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `alumnos`
--
ALTER TABLE `alumnos`
  MODIFY `ID_Alumno` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=15;

--
-- AUTO_INCREMENT de la tabla `cuartosemestre`
--
ALTER TABLE `cuartosemestre`
  MODIFY `id_Codigo` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;

--
-- AUTO_INCREMENT de la tabla `primersemestre`
--
ALTER TABLE `primersemestre`
  MODIFY `id_Codigo` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;

--
-- AUTO_INCREMENT de la tabla `quintosemestre`
--
ALTER TABLE `quintosemestre`
  MODIFY `id_Codigo` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;

--
-- AUTO_INCREMENT de la tabla `segundosemestre`
--
ALTER TABLE `segundosemestre`
  MODIFY `id_Codigo` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;

--
-- AUTO_INCREMENT de la tabla `sextosemestre`
--
ALTER TABLE `sextosemestre`
  MODIFY `id_Codigo` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;

--
-- AUTO_INCREMENT de la tabla `tercersemestre`
--
ALTER TABLE `tercersemestre`
  MODIFY `id_Codigo` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
