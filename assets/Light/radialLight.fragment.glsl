#ifdef GL_ES
precision mediump float;
#endif

uniform vec2 u_lightPos;
uniform float u_radius;
uniform vec4 u_color;

varying vec2 v_texCoord;

void main() {
    float dist = distance(v_texCoord, u_lightPos);
    float intensity = 1.0 - smoothstep(0.0, u_radius, dist);
intensity = pow(intensity, 2.0);
gl_FragColor = vec4(u_color.rgb * intensity, intensity * 0.3);
}
