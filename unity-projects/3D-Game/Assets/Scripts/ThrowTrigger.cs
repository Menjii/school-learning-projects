using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using UnityEngine.UI;

public class ThrowTrigger : MonoBehaviour
{
    public RawImage crosshair;
    // Start is called before the first frame update
    void Start()
    {
        
    }

    // Update is called once per frame
    void Update()
    {
        
    }

    void OnTriggerEnter(Collider col) {
        crosshair.enabled = true;
        if(col.gameObject.tag == "Player") {
            CoconutThrower.canThrow=true;
        }
    }
    void OnTriggerExit(Collider col){
        crosshair.enabled = false;
        if(col.gameObject.tag == "Player") {
            CoconutThrower.canThrow=false;
        }
    }
}
