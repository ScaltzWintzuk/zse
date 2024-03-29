#version 460 core

layout(location = 0) in vec3 positionIn;
layout(location = 1) in vec4 colorIn;
layout(location = 2) in vec2 texCoordIn;

out vec4 passColor;
out vec2 passTexCoord;

uniform mat4 transform;
uniform mat4 view;
uniform mat4 projection;

void main() {
	gl_Position = projection * view * transform * vec4(positionIn, 1.0);
	passColor = colorIn;
	passTexCoord = texCoordIn;
}