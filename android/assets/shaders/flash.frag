#ifdef GL_ES
precision mediump float;
#endif
 
varying vec4 v_color;
varying vec2 v_texCoords;
uniform sampler2D u_texture;
uniform float u_whiteness;
 
void main() {
    vec4 c = v_color * texture2D(u_texture, v_texCoords);
    float grey = dot( c.rgb, vec3(0.9, 0.9, 0.9) );
    vec3 blendedColor = mix(c.rgb, vec3(grey), u_whiteness);
    gl_FragColor = vec4(blendedColor.rgb, c.a);
};