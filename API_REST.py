from flask import Flask, jsonify, request, abort

app = Flask(__name__)

# Almacena los objetos en memoria
alumnos = []
profesores = []

# Validación de los datos de Alumno
def validate_alumno(data):
    required_fields = ["id", "nombres", "apellidos", "matricula", "promedio"]
    if not isinstance(data.get("promedio"), (int, float)):
        return False
    for field in required_fields:
        if field not in data or not data[field]:
            return False
    return True

# Validación de los datos de Profesor
def validate_profesor(data):
    required_fields = ["id", "numeroEmpleado", "nombres", "apellidos", "horasClase"]
    if not isinstance(data.get("horasClase"), (int, float)):
        return False
    for field in required_fields:
        if field not in data or not data[field]:
            return False
    return True

# --------------------- Endpoints de Alumnos ---------------------

@app.route('/alumnos', methods=['GET'])
def get_alumnos():
    return jsonify(alumnos), 200

@app.route('/alumnos/<int:id>', methods=['GET'])
def get_alumno(id):
    alumno = next((a for a in alumnos if a["id"] == id), None)
    if not alumno:
        return jsonify({"error": "Alumno not found"}), 404
    return jsonify(alumno), 200

@app.route('/alumnos', methods=['POST'])
def create_alumno():
    data = request.json
    if not validate_alumno(data):
        return jsonify({"error": "Invalid data"}), 400
    alumnos.append(data)
    return jsonify(data), 201

@app.route('/alumnos/<int:id>', methods=['PUT'])
def update_alumno(id):
    data = request.json
    if not validate_alumno(data):
        return jsonify({"error": "Invalid data"}), 400
    alumno = next((a for a in alumnos if a["id"] == id), None)
    if not alumno:
        return jsonify({"error": "Alumno not found"}), 404
    alumno.update(data)
    return jsonify(alumno), 200

@app.route('/alumnos/<int:id>', methods=['DELETE'])
def delete_alumno(id):
    global alumnos
    alumnos = [a for a in alumnos if a["id"] != id]
    return jsonify({"message": "Alumno deleted"}), 200

# --------------------- Endpoints de Profesores ---------------------

@app.route('/profesores', methods=['GET'])
def get_profesores():
    return jsonify(profesores), 200

@app.route('/profesores/<int:id>', methods=['GET'])
def get_profesor(id):
    profesor = next((p for p in profesores if p["id"] == id), None)
    if not profesor:
        return jsonify({"error": "Profesor not found"}), 404
    return jsonify(profesor), 200

@app.route('/profesores', methods=['POST'])
def create_profesor():
    data = request.json
    if not validate_profesor(data):
        return jsonify({"error": "Invalid data"}), 400
    profesores.append(data)
    return jsonify(data), 201

@app.route('/profesores/<int:id>', methods=['PUT'])
def update_profesor(id):
    data = request.json
    if not validate_profesor(data):
        return jsonify({"error": "Invalid data"}), 400
    profesor = next((p for p in profesores if p["id"] == id), None)
    if not profesor:
        return jsonify({"error": "Profesor not found"}), 404
    profesor.update(data)
    return jsonify(profesor), 200

@app.route('/profesores/<int:id>', methods=['DELETE'])
def delete_profesor(id):
    global profesores
    profesores = [p for p in profesores if p["id"] != id]
    return jsonify({"message": "Profesor deleted"}), 200

if __name__ == '__main__':
    app.run(debug=True, host='0.0.0.0')