<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">

  <xsl:output method="html" encoding="UTF-8" indent="yes"/>

  <xsl:template match="/">
    <html lang="es">
      <head>
        <meta charset="UTF-8"/>
        <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
        <title>Ranking Pokemon</title>
        <link rel="stylesheet" href="EstiloRanking.css"/>
      </head>
      <body>
        <main>
          <h1>RANKING POKEMON</h1>
          <table>
            <tr>
              <th>POSICION</th>
              <th>JUGADOR</th>
              <th>PUNTUACION</th>
            </tr>
            <!-- Bucle para recorrer los jugadores y ordenarlos de mayor a menor puntuación -->
            <xsl:for-each select="ranking/jugador">
              <xsl:sort select="puntuacion" data-type="number" order="descending"/>
              <tr>
                <td><xsl:value-of select="position()"/></td>
                <td><xsl:value-of select="nombre"/></td>
                <td><xsl:value-of select="puntuacion"/> puntos</td>
              </tr>
            </xsl:for-each>
          </table>
        </main>
      </body>
    </html>
  </xsl:template>
</xsl:stylesheet>