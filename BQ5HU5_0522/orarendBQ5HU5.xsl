<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
<xsl:output method="html" encoding="UTF-8" indent="yes"/>
<xsl:template match="/">
<html><head><meta charset="UTF-8"/><title>XY Órarend – 2026. II. félév</title>
<style>body{font-family:Arial;padding:30px}
table{border-collapse:collapse;
width:100%}
th,td{border:1px solid #333;padding:8px}
th{background:#ddd}
</style>
</head><body>
<h1>XY Órarend – 2026. II. félév</h1>
<table>
<tr><th>ID</th><th>Típus</th><th>Kurzus</th><th>Nap</th><th>Tól</th><th>Ig</th><th>Helyszín</th><th>Oktató</th><th>Szak</th></tr>
<xsl:for-each select="BQ5HU5_orarend/ora">
<tr>
<td><xsl:value-of select="@id"/></td><td><xsl:value-of select="@tipus"/></td><td><xsl:value-of select="kurzus"/></td><td><xsl:value-of select="idopont/nap"/></td><td><xsl:value-of select="idopont/tol"/></td><td><xsl:value-of select="idopont/ig"/></td><td><xsl:value-of select="helyszin"/></td><td><xsl:value-of select="oktato"/></td><td><xsl:value-of select="szak"/></td>
</tr>
</xsl:for-each>
</table></body></html>
</xsl:template>
</xsl:stylesheet>
